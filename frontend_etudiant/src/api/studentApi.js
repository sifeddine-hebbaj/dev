import axiosInstance from "./axiosInstance";

export const fetchStudents = async () => {
    const response = await axiosInstance.get("/ETUDIANT-SERVICE/api/students");
    return response.data;
};

export const fetchStudentCount = async () => {
    const response = await axiosInstance.get("/ETUDIANT-SERVICE/api/students/count");
    return response.data;
}


export const fetchStudentById = async (id) => {
    const response = await axiosInstance.get(`/ETUDIANT-SERVICE/api/students/${id}`);

    return response.data;
}

export const addStudent = async (student) => {
    const response = await axiosInstance.post("/ETUDIANT-SERVICE/api/students", student);
    return response.data;
};


export const deleteStudent = async (id) => {
    const response = await axiosInstance.delete(`/ETUDIANT-SERVICE/api/students/${id}`);
    return response.data;
};


export const updateStudent = async (id, student) => {
    const response = await axiosInstance.put(`/ETUDIANT-SERVICE/api/students/${id}`, student);
    return response.data;
};