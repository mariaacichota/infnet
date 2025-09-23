package com.infnet.aluno.service;

@SpringBootTest
@ActiveProfiles("test")
class MatriculaServiceTest {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Test
    void deveMatricularEAdicionarNota() {
        Aluno aluno = alunoService.cadastrar(new Aluno(null, "João", "12345678900", "joao@email.com", "9999", "Rua A", null));
        Disciplina disciplina = disciplinaService.cadastrar(new Disciplina(null, "História", "HIS101", null));

        Matricula matricula = matriculaService.matricular(aluno.getId(), disciplina.getId());
        assertNotNull(matricula.getId());

        Matricula atualizado = matriculaService.atribuirNota(matricula.getId(), 8.5);
        assertEquals(8.5, atualizado.getNota());
    }
}
