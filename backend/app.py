from flask import Flask, request, jsonify
from flask_cors import CORS
from lxml import etree
import yaml
import os

app = Flask(__name__)

# Explicit CORS configuration to allow requests from localhost:3000
CORS(app, resources={r"/*": {"origins": "http://localhost:3000"}})

# Function to validate the XML file against the XSD schema
def validate_xml(xml_string):
    try:
        # Load the XML schema (config.xsd) and validate the XML string against it
        xmlschema_doc = etree.parse('config.xsd')
        xmlschema = etree.XMLSchema(xmlschema_doc)
        doc = etree.fromstring(xml_string)
        xmlschema.assertValid(doc)  # Validates the XML
        return True, "XML is valid"
    except etree.XMLSchemaError as e:
        return False, f"XML validation error: {str(e)}"

# Function to transform XML to YAML using XSLT
def transform_to_yaml_with_xslt(xml_string, xslt_filename, output_filename):
    try:
        # Load and parse the XSLT file
        xslt_doc = etree.parse(xslt_filename)
        transform = etree.XSLT(xslt_doc)
        
        # Load and parse the XML string
        xml_doc = etree.fromstring(xml_string)
        
        # Apply the XSLT transformation
        result_tree = transform(xml_doc)
        
        # Convert the XSLT result to string
        yaml_content = str(result_tree)

        # Convert the string result to YAML
        yaml_content = yaml.dump(yaml.safe_load(yaml_content), default_flow_style=False)

        # Ensure the 'generated_yamls' directory exists
        os.makedirs('generated_yamls', exist_ok=True)

        # Save the YAML content to a file
        with open(output_filename, 'w') as file:
            file.write(yaml_content)

        return yaml_content
    
    except Exception as e:
        return f"Error during XSLT transformation: {str(e)}"

@app.route('/')
def index():
    return "Welcome to the Flask app!"

@app.route('/validate_and_generate', methods=['POST'])
def validate_and_generate():
    try:
        # Check if a file has been uploaded
        if 'xml_file' not in request.files:
            return jsonify({"status": "error", "message": "No XML file uploaded"}), 400

        xml_file = request.files['xml_file']
        
        # Read the uploaded XML file as a string
        xml_string = xml_file.read().decode('utf-8')
        
        # Validate the XML file
        is_valid, message = validate_xml(xml_string)
        
        if is_valid:
            # Get the requested format (Docker or Kubernetes)
            format_type = request.form.get('format', 'docker')  # Default is 'docker'

            # Set output filenames based on the format
            docker_filename = 'generated_yamls/docker-compose.yaml'
            kubernetes_filename = 'generated_yamls/kubernetes.yml'

            # Transform the XML into Docker Compose YAML
            docker_yaml_content = transform_to_yaml_with_xslt(xml_string, 'xml2dockercompose.xslt', docker_filename)
            
            # Transform the XML into Kubernetes YAML
            kubernetes_yaml_content = transform_to_yaml_with_xslt(xml_string, 'xml2kubernetes.xslt', kubernetes_filename)
            
            if 'Error' in docker_yaml_content or 'Error' in kubernetes_yaml_content:
                return jsonify({"status": "error", "message": "Error during transformation"}), 500

            return jsonify({
                "status": "success",
                "docker_yaml": docker_yaml_content,
                "kubernetes_yaml": kubernetes_yaml_content
            }), 200
        else:
            return jsonify({"status": "error", "message": message}), 400

    except Exception as e:
        return jsonify({"status": "error", "message": f"An error occurred: {str(e)}"}), 500

if __name__ == '__main__':
    app.run(debug=True)
