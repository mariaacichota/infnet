package com.infnet.aluno.service;

import com.infnet.aluno.model.Aluno;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AlunoServiceTest {

    @Autowired
    private AlunoService alunoService;

    @Test
    void deveCadastrarAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("Maria");
        aluno.setCpf("12345678900");
        aluno.setEmail("maria@email.com");
        aluno.setTelefone("999999999");
        aluno.setEndereco("Rua B");

        Aluno salvo = alunoService.cadastrar(aluno);

        assertNotNull(salvo.getId());
        assertEquals("Maria", salvo.getNome());
    }
}