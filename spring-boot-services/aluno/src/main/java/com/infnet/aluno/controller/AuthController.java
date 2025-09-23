package com.infnet.aluno.controller;

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