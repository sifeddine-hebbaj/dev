import axiosInstance from "./axiosInstance";

export const fetchCourses = async () => {
    const response = await axiosInstance.get("/COURS-SERVICE/api/courses");
    return response.data;
};

export const fetchSyllabus = async () => {
    const response = await axiosInstance.get("/COURS-SERVICE/api/courses/syllabus");
    return response.data;
};

export const fetchSyllabusById = async (id) => {
    const response = await axiosInstance.get(`/COURS-SERVICE/api/courses/syllabus/${id}`);

    return response.data;
}

export const fetchCourseById = async (id) => {
    const response = await axiosInstance.get(`/COURS-SERVICE/api/courses/${id}`);

    return response.data;
}


export const fetchCourseCount = async () => {
    const response = await axiosInstance.get("/COURS-SERVICE/api/courses/count");
    return response.data;
}



export const addCourse = async (Course) => {
    const response = await axiosInstance.post("/COURS-SERVICE/api/courses", Course);
    return response.data;
}

export const deleteCourse = async (id) => {
    const response = await axiosInstance.delete(`/COURS-SERVICE/api/courses/${id}`);
    return response.data;
};


export const updateCourse = async (id, course) => {
    const response = await axiosInstance.put(`/COURS-SERVICE/api/courses/${id}`, course);
    return response.data;
};

export const updateSyllabus = async (id, syllabus) => {
    const response = await axiosInstance.put(`/COURS-SERVICE/api/courses/syllabus/${id}`, syllabus);
    return response.data;
};


export const addSyllabus = async (syllabus) => {
    const response = await axiosInstance.post("/COURS-SERVICE/api/courses/syllabus", syllabus);
    return response.data;
}

export const assignSyllabusToCourse = async (courseId, syllabusId) => {
    const response = await axiosInstance.post(`/COURS-SERVICE/api/courses/assignSyllabus/${courseId}/${syllabusId}`);
    return response.data;
};


