package com.infnet.aluno.controller;

import com.infnet.aluno.model.Aluno;
import com.infnet.aluno.service.AlunoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public Aluno cadastrar(@RequestBody Aluno aluno) {
        return alunoService.cadastrar(aluno);
    }

    @GetMapping
    public List<Aluno> listar() {
        return alunoService.listarTodos();
    }
}

