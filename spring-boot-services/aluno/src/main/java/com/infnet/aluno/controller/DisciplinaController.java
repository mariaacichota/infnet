package com.infnet.aluno.controller;

import com.infnet.aluno.model.Disciplina;
import com.infnet.aluno.service.DisciplinaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping
    public Disciplina cadastrar(@RequestBody Disciplina disciplina) {
        return disciplinaService.cadastrar(disciplina);
    }

    @GetMapping
    public List<Disciplina> listar() {
        return disciplinaService.listarTodas();
    }
}
