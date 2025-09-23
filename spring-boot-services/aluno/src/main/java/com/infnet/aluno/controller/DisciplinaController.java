package com.infnet.aluno.controller;

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
