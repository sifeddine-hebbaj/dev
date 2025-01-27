package com.idld.resultatservice.service;

import com.idld.resultatservice.Dtos.CourseDto;
import com.idld.resultatservice.Dtos.ResultDTORequest;
import com.idld.resultatservice.Dtos.ResultDto;
import com.idld.resultatservice.Dtos.StudentDto;
import com.idld.resultatservice.entities.Result;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ResultServiceInterf {

    //create a new result
    Result createResult(ResultDTORequest resultDto);

    //fetch by a student
    List<ResultDto> getResultByStudent(long studentId);

    //fetch for a specific course
    List<ResultDto> getResultByCourse(long courseId);

    //update a result
    Result updateResult(long resultId, ResultDto resultDto);

    //delete a result
    void deleteResult(long resultId);

    //test
    public StudentDto getStudentById(long studentId);
    public CourseDto getCourseById(long courseId);

    public List<ResultDto> getStudentsWithGradesByCourse(long courseId);

    public void applyBatchGrades(List<ResultDTORequest> results);
}