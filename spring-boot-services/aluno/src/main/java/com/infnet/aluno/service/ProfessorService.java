package com.infnet.aluno.service;

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