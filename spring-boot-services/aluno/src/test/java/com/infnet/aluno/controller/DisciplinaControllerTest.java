package com.infnet.aluno.controller;

import com.infnet.aluno.model.Disciplina;
import com.infnet.aluno.service.DisciplinaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DisciplinaController.class)
class DisciplinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisciplinaService disciplinaService;

    @Test
    void deveCadastrarDisciplina() throws Exception {
        Disciplina d = new Disciplina();
        d.setId(1L);
        d.setNome("Matemática");

        when(disciplinaService.cadastrar(any(Disciplina.class))).thenReturn(d);

        mockMvc.perform(post("/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Matemática\",\"codigo\":\"MAT101\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Matemática"));
    }
}
