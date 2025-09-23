package com.infnet.aluno.service;

@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public Matricula matricular(Long alunoId, Long disciplinaId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow();
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow();
        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setDisciplina(disciplina);
        return matriculaRepository.save(matricula);
    }

    public Matricula atribuirNota(Long matriculaId, Double nota) {
        Matricula matricula = matriculaRepository.findById(matriculaId).orElseThrow();
        matricula.setNota(nota);
        return matriculaRepository.save(matricula);
    }

    public List<Aluno> listarAprovados(Long disciplinaId) {
        return matriculaRepository.findByDisciplinaIdAndNotaGreaterThanEqual(disciplinaId, 7.0)
                .stream().map(Matricula::getAluno).toList();
    }

    public List<Aluno> listarReprovados(Long disciplinaId) {
        return matriculaRepository.findByDisciplinaIdAndNotaLessThan(disciplinaId, 7.0)
                .stream().map(Matricula::getAluno).toList();
    }
}