package com.idld.profservice.controller;

import com.idld.profservice.dtos.TeacherDtoRequest;
import com.idld.profservice.dtos.TeacherDtoResponse;
import com.idld.profservice.service.TeacherServiceInter;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
@RestController
@RequestMapping("/api")
public class TeacherController {
    private final TeacherServiceInter teacherServiceInter;

    public TeacherController(TeacherServiceInter teacherServiceInter) {
        this.teacherServiceInter = teacherServiceInter;
    }

    @GetMapping("/teachers")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<TeacherDtoResponse> getTeachers() {
        return teacherServiceInter.getAllTeachers();
    }

    @GetMapping("/teachers/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public TeacherDtoResponse getTeacherById(@PathVariable long id) {
        return teacherServiceInter.getTeacherById(id);
    }

    @PostMapping("/teachers")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void create(@RequestBody @Valid TeacherDtoRequest teacherDtoRequest) {
        teacherServiceInter.addTeacher(teacherDtoRequest);
    }

    @PutMapping("/teachers/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void update(@PathVariable long id, @RequestBody TeacherDtoRequest teacherDtoRequest) {
        teacherServiceInter.updateTeacher(id, teacherDtoRequest);
    }

    @DeleteMapping("/teachers/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void delete(@PathVariable long id) {
        teacherServiceInter.deleteTeacher(id);
    }

    @RequestMapping("/test")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public String test() {
        return "Controller is working!";
    }


    @GetMapping("/teachers/count")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public long getTeachersCount(){
        return teacherServiceInter.getTotalTeachersCount();
    }
}
