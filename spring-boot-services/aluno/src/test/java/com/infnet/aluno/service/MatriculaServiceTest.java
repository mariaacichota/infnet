package com.infnet.aluno.service;

import com.infnet.aluno.model.Aluno;
import com.infnet.aluno.model.Disciplina;
import com.infnet.aluno.model.Matricula;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
