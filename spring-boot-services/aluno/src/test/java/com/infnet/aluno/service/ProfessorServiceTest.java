package com.infnet.aluno.service;

@SpringBootTest
@ActiveProfiles("test")
class ProfessorServiceTest {

    @Autowired
    private ProfessorService professorService;

    @Test
    void deveRegistrarProfessor() {
        Professor prof = new Professor();
        prof.setUsername("admin");
        prof.setPassword("1234");

        Professor salvo = professorService.registrar(prof);

        assertNotNull(salvo.getId());
        assertNotEquals("1234", salvo.getPassword()); // senha deve ser codificada
    }
}
