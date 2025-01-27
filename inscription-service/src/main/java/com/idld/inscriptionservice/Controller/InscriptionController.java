package com.idld.inscriptionservice.Controller;


import com.idld.inscriptionservice.DTOs.RequestInscriptionDTO;
import com.idld.inscriptionservice.DTOs.ResponseInscriptionDTO;
import com.idld.inscriptionservice.DTOs.courseDTO;
import com.idld.inscriptionservice.Model.Student;
import com.idld.inscriptionservice.Service.InscriptionServiceInterface;
import com.idld.inscriptionservice.Service.CourseFeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.idld.inscriptionservice.DTOs.AssignCoursesRequestDTO;


import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController implements ControllerInterface{
    private final InscriptionServiceInterface inscriptionService;
    private final CourseFeignClient courseFeignClient;

    public InscriptionController(InscriptionServiceInterface inscriptionService, CourseFeignClient courseFeignClient) {
        this.inscriptionService = inscriptionService;
        this.courseFeignClient = courseFeignClient;
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseInscriptionDTO inscrireEtudiant(@RequestBody RequestInscriptionDTO requestInscriptionDTO) {
        return inscriptionService.inscrireEtudiant(requestInscriptionDTO);
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<ResponseInscriptionDTO> getAllInscriptions() {
        return inscriptionService.getAllInscriptions();
    }


    @Override
    @PostMapping("/assign-courses")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> assignCoursesToStudent(@RequestBody AssignCoursesRequestDTO assignCoursesRequestDTO) {
        try {
            inscriptionService.assignCoursesToStudent(assignCoursesRequestDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @Override
    @GetMapping("/CoursesByStudentId/{studentId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<courseDTO> getCoursesForStudent(@PathVariable Long studentId) {
        // Fetch course IDs for the student
        List<Long> courseIds = inscriptionService.findCourseIdsByStudentId(studentId);

        // Fetch course details for each ID using Feign client
        return courseIds.stream()
                .map(courseFeignClient::getCourseById)
                .collect(Collectors.toList());
    }



    @GetMapping("/course/{courseId}/students")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<Student>> getStudentsByCourseId(@PathVariable Long courseId) {
        try {
            List<Student> students = inscriptionService.findStudentsByCourseId(courseId);
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}












