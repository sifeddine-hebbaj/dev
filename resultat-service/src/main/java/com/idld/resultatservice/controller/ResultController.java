package com.idld.resultatservice.controller;

import com.idld.resultatservice.Dtos.CourseDto;
import com.idld.resultatservice.Dtos.ResultDTORequest;
import com.idld.resultatservice.Dtos.ResultDto;
import com.idld.resultatservice.Dtos.StudentDto;
import com.idld.resultatservice.KafkaGradeConsumer.KafkaConsumerService;
import com.idld.resultatservice.entities.Result;
import com.idld.resultatservice.service.ResultServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
@RestController
@RequestMapping("/api/results") // Base URL for all endpoints in this controller
public class ResultController {

        private final KafkaConsumerService kafkaConsumerService;

        private final ResultServiceInterf resultService;

        // Constructor Injection
        public ResultController(ResultServiceInterf resultService,KafkaConsumerService  kafkaConsumerService) {
            this.resultService = resultService;
            this. kafkaConsumerService =  kafkaConsumerService;
        }


    //testing the communication
    @GetMapping("/student-info/{studentId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<StudentDto> getStudentInfo(@PathVariable long studentId) {
        StudentDto student = resultService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/course-info/{courseId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<CourseDto> getCoursetInfo(@PathVariable long courseId) {
        CourseDto course = resultService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }


    // Create a new result
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Result> createResult(@RequestBody ResultDTORequest resultDto) {
        Result result = resultService.createResult(resultDto);
        return ResponseEntity.ok(result); // Respond with HTTP 200 and the created result
    }

    // Get results by student ID

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<ResultDto>> getResultsByStudent(@PathVariable long studentId) {
        List<ResultDto> results = resultService.getResultByStudent(studentId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/studentKafka/{studentId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<ResultDto>> getResultsByStudentKafka(@PathVariable long studentId) {
        // Get all results
        List<ResultDto> allResults = kafkaConsumerService.getResultDtos();
        List<ResultDto> filteredResults = new ArrayList<>();

        // Using for loop to filter the results based on studentId
        for (ResultDto result : allResults) {
            if (result.getStudentId() == studentId) {
                filteredResults.add(result);
            }
        }

        // Return the filtered results wrapped in ResponseEntity
        if (filteredResults.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 if no results found
        } else {
            return ResponseEntity.ok(filteredResults); // Return 200 with the list of results
        }
    }




    // Get results by course ID
    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<ResultDto>> getResultsByCourse(@PathVariable long courseId) {
        List<ResultDto> results = resultService.getResultByCourse(courseId);
        return ResponseEntity.ok(results);
    }

    //student infos and grade by courseId
    @GetMapping("/course/{courseId}/students-grades")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<ResultDto> getStudentsWithGradesByCourse(@PathVariable long courseId){
        return resultService.getStudentsWithGradesByCourse(courseId);
    }





    // Update a result
    @PutMapping("/{resultId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Result> updateResult(@PathVariable long resultId, @RequestBody ResultDto resultDto) {
        Result updatedResult = resultService.updateResult(resultId, resultDto);
        return ResponseEntity.ok(updatedResult);
    }

    // Delete a result
    @DeleteMapping("/{resultId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deleteResult(@PathVariable long resultId) {
        resultService.deleteResult(resultId);
        return ResponseEntity.noContent().build(); // Respond with HTTP 204 (No Content)
    }


    @PostMapping("/batch")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<String> applyBatchGrades(@RequestBody List<ResultDTORequest> results) {
        try {
            resultService.applyBatchGrades(results);
            return ResponseEntity.ok("Batch grades applied successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error applying batch grades: " + e.getMessage());
        }

    }

}
