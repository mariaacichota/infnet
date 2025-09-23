package com.infnet.aluno.service;

import com.infnet.aluno.model.Professor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

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
        assertNotEquals("1234", salvo.getPassword());
    }
}
