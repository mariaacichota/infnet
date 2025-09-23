package com.infnet.aluno.controller;

import com.infnet.aluno.model.Professor;
import com.infnet.aluno.service.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ProfessorService professorService;

    public AuthController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/register")
    public ResponseEntity<Professor> registrar(@RequestBody Professor professor) {
        return ResponseEntity.ok(professorService.registrar(professor));
    }
}