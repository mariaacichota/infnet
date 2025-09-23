package com.infnet.aluno.controller;

import com.infnet.aluno.model.Aluno;
import com.infnet.aluno.model.Matricula;
import com.infnet.aluno.service.MatriculaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping("/{alunoId}/{disciplinaId}")
    public Matricula matricular(@PathVariable Long alunoId, @PathVariable Long disciplinaId) {
        return matriculaService.matricular(alunoId, disciplinaId);
    }

    @PostMapping("/{matriculaId}/nota/{nota}")
    public Matricula atribuirNota(@PathVariable Long matriculaId, @PathVariable Double nota) {
        return matriculaService.atribuirNota(matriculaId, nota);
    }

    @GetMapping("/aprovados/{disciplinaId}")
    public List<Aluno> listarAprovados(@PathVariable Long disciplinaId) {
        return matriculaService.listarAprovados(disciplinaId);
    }

    @GetMapping("/reprovados/{disciplinaId}")
    public List<Aluno> listarReprovados(@PathVariable Long disciplinaId) {
        return matriculaService.listarReprovados(disciplinaId);
    }
}
