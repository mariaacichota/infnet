package com.infnet.aluno.service;

import com.infnet.aluno.model.Disciplina;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class DisciplinaServiceTest {

    @Autowired
    private DisciplinaService disciplinaService;

    @Test
    void deveCadastrarDisciplina() {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Matemática");
        disciplina.setCodigo("MAT101");

        Disciplina salvo = disciplinaService.cadastrar(disciplina);

        assertNotNull(salvo.getId());
        assertEquals("Matemática", salvo.getNome());
    }
}
