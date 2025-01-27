package com.idld.coursservice.Controller;

import com.idld.coursservice.DTO.CourseRequestDTO;
import com.idld.coursservice.DTO.CourseResponseDTO;
import com.idld.coursservice.Entity.Course;
import com.idld.coursservice.Entity.Syllabus;
import com.idld.coursservice.Service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{courseId}/details")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public CourseResponseDTO getCourseDetails(@PathVariable long courseId) {
        return courseService.getCourseDetails(courseId);
    }

    @GetMapping("/count")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public long getCoursesCount(){
        return courseService.getTotalCoursesCount();
    }


    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CourseResponseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }



    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public CourseResponseDTO getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CourseResponseDTO createCourse(@RequestBody CourseRequestDTO courseRequestDTO) {
        return courseService.createCourse(courseRequestDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void updateCourse(@PathVariable Long id, @RequestBody CourseRequestDTO courseRequestDTO) {
        courseService.updateCourse(id, courseRequestDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CourseResponseDTO deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourse(id);
    }

    @PostMapping("/syllabus")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Syllabus createSyllabus(@RequestBody Syllabus syllabus) {
        return courseService.createSyllabus(syllabus);
    }

    @PostMapping("/assignSyllabus/{courseId}/{syllabusId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Course> assignSyllabus(@PathVariable Long courseId, @PathVariable Long syllabusId) {
        Course updatedCourse = courseService.assignSyllabus(courseId, syllabusId);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @GetMapping("/syllabus")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<Syllabus> getAllSyllabus(){
        return courseService.getAllSyllabus();
    }

    @GetMapping("/syllabus/{syllabusId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public Syllabus getSyllabusById(@PathVariable Long syllabusId) {
        return courseService.getSyllabusById(syllabusId);
    }


    @PutMapping("/syllabus/{syllabusId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Syllabus updateSyllabus(@PathVariable Long syllabusId, @RequestBody Syllabus syllabus) {
        return courseService.updateSyllabus(syllabusId, syllabus);
    }

    @DeleteMapping("/syllabus/{syllabusId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteSyllabus(@PathVariable Long syllabusId) {
        courseService.deleteSyllabus(syllabusId);
    }

}
