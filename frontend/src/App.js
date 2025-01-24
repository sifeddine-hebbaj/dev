import React, { useState, useEffect } from 'react';

function App() {
  const [xmlFile, setXmlFile] = useState(null);
  const [validationMessage, setValidationMessage] = useState('');
  const [yamlOutput, setYamlOutput] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [isValid, setIsValid] = useState(false);
  const [format, setFormat] = useState('docker');
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleFileChange = (event) => {
    setXmlFile(event.target.files[0]);
  };

  const handleFormatChange = (event) => {
    setFormat(event.target.value);
  };

  const handleValidate = async (event) => {
    event.preventDefault();
    if (!xmlFile) {
      setErrorMessage('Please choose a file to upload');
      setValidationMessage('');
      setIsValid(false);
      return;
    }

    const formData = new FormData();
    formData.append('xml_file', xmlFile);
    formData.append('format', format);

    try {
      const response = await fetch('http://localhost:5000/validate_and_generate', {
        method: 'POST',
        body: formData,
      });

      const result = await response.json();
      if (result.status === 'success') {
        setIsValid(true);
        setValidationMessage('Validation successful!');
        setErrorMessage('');
      } else {
        setIsValid(false);
        setValidationMessage('');
        setErrorMessage(result.message);
      }
    } catch (error) {
      setErrorMessage('An error occurred: ' + error.message);
      setValidationMessage('');
      setIsValid(false);
    }
  };

  const handleGenerateYaml = async (event) => {
    event.preventDefault();
    if (!isValid) {
      setErrorMessage('XML file is not valid. Cannot generate YAML.');
      return;
    }

    const formData = new FormData();
    formData.append('xml_file', xmlFile);
    formData.append('format', format);

    try {
      const response = await fetch('http://localhost:5000/validate_and_generate', {
        method: 'POST',
        body: formData,
      });

      const result = await response.json();
      if (result.status === 'success') {
        setYamlOutput(format === 'docker' ? result.docker_yaml : result.kubernetes_yaml);
        setErrorMessage('');
      } else {
        setYamlOutput('');
        setErrorMessage(result.message);
      }
    } catch (error) {
      setYamlOutput('');
      setErrorMessage('An error occurred: ' + error.message);
    }
  };

  const handleDownloadYaml = () => {
    const blob = new Blob([yamlOutput], { type: 'application/x-yaml' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = format === 'docker' ? 'docker-compose.yaml' : 'kubernetes.yaml';
    link.click();
  };

  const openHelpModal = () => {
    setIsModalOpen(true);
  };

  const closeHelpModal = () => {
    setIsModalOpen(false);
  };

  return (
    <div className="App">
      <header>
        <h1>DevOps Configuration Manager</h1>
        <button onClick={openHelpModal}>Help</button>
      </header>
      <form>
        <label>
          Choose XML file:
          <input type="file" onChange={handleFileChange} />
        </label>
        <div>
          <label>
            Format:
            <select value={format} onChange={handleFormatChange}>
              <option value="docker">Docker Compose</option>
              <option value="kubernetes">Kubernetes</option>
            </select>
          </label>
        </div>
        <div>
          <button type="button" onClick={handleValidate}>Validate</button>
          <button type="button" onClick={handleGenerateYaml} disabled={!isValid}>Generate YAML</button>
        </div>
      </form>

      {validationMessage && (
        <div>
          <h3>Validation Result:</h3>
          <p>{validationMessage}</p>
        </div>
      )}

      {yamlOutput && (
        <div>
          <h3>Generated YAML:</h3>
          <pre>{yamlOutput}</pre>
          <button type="button" onClick={handleDownloadYaml}>Download YAML</button>
        </div>
      )}

      {errorMessage && (
        <div className="error-message">
          <p>{errorMessage}</p>
        </div>
      )}

      {isModalOpen && (
        <div className="modal">
          <div className="modal-content">
            <span className="close" onClick={closeHelpModal}>&times;</span>
            <h3>XML Structure Instructions:</h3>
            <p>Your XML should have the following structure:</p>
            <pre>{`<config>
  <environments>
    <dev>
      <container>
        <name>web-server</name>
        <image>nginx:latest</image>
        <ports>80</ports>
      </container>
      <container>
        <name>database</name>
        <image>mysql:5.7</image>
        <ports>3306</ports>
      </container>
    </dev>
    <prod>
      <container>
        <name>redis-cache</name>
        <image>redis:alpine</image>
        <ports>6379</ports>
      </container>
    </prod>
  </environments>
</config>`}</pre>
            <p>Ensure that your XML follows this structure, including the container names, images, and ports.</p>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;
