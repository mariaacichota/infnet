package com.infnet.aluno.service;

import com.infnet.aluno.model.Professor;
import com.infnet.aluno.repository.ProfessorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfessorService(ProfessorRepository professorRepository, PasswordEncoder passwordEncoder) {
        this.professorRepository = professorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Professor registrar(Professor professor) {
        professor.setPassword(passwordEncoder.encode(professor.getPassword()));
        return professorRepository.save(professor);
    }

    public Optional<Professor> buscarPorUsername(String username) {
        return professorRepository.findByUsername(username);
    }
}