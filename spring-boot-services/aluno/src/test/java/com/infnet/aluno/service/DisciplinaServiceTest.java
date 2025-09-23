package com.infnet.aluno.service;

@SpringBootTest
@ActiveProfiles("test")
class DisciplinaServiceTest {

    @Autowired
    private DisciplinaService disciplinaService;

    @Test
    void deveCadastrarDisciplina() {
        Disciplina d = new Disciplina();
        d.setNome("Matemática");
        d.setCodigo("MAT101");

        Disciplina salvo = disciplinaService.cadastrar(d);

        assertNotNull(salvo.getId());
        assertEquals("Matemática", salvo.getNome());
    }
}
