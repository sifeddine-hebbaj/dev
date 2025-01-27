package com.idld.inscriptionservice.Controller;

import com.idld.inscriptionservice.DTOs.AssignCoursesRequestDTO;
import com.idld.inscriptionservice.DTOs.RequestInscriptionDTO;
import com.idld.inscriptionservice.DTOs.ResponseInscriptionDTO;
import com.idld.inscriptionservice.DTOs.courseDTO;
import com.idld.inscriptionservice.Model.Course;
import com.idld.inscriptionservice.Service.InscriptionServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ControllerInterface {
    public ResponseInscriptionDTO inscrireEtudiant(@RequestBody RequestInscriptionDTO requestInscriptionDTO) ;
    public List<ResponseInscriptionDTO> getAllInscriptions();

    public ResponseEntity<?> assignCoursesToStudent(@RequestBody AssignCoursesRequestDTO assignCoursesRequestDTO);

    public List<courseDTO> getCoursesForStudent(@PathVariable Long studentId);

}