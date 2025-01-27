import axiosInstance from "./axiosInstance";

export const AssignCoursesToStudent = async (AssignCoursesReqestDTO) => {
    const response = await axiosInstance.post("/INSCRIPTION-SERVICE/api/inscriptions/assign-courses", AssignCoursesReqestDTO);
    return response.data;
}

export const getCoursesByStudentId = async (studentId) => {
    const response = await axiosInstance.get(`/INSCRIPTION-SERVICE/api/inscriptions/CoursesByStudentId/${studentId}`);
    return response.data;
}

export const fetchStudentsByCourseId = async (courseId) => {
    const response = await axiosInstance.get(`/INSCRIPTION-SERVICE/api/inscriptions/course/${courseId}/students`);
    return response.data;
}