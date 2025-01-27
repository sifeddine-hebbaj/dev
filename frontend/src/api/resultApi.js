import axiosInstance from "./axiosInstance.js";

export const fetchStudentsGradeByCoursId = async (courseId) => {
    const response = await axiosInstance.get(`/RESULTAT-SERVICE/api/results/course/${courseId}/students-grades`);
    return response.data;
}

export const applyBatchGrades = async (results) => {
    const response = await axiosInstance.post(`/RESULTAT-SERVICE/api/results/batch`, results);
    return response.data;
}