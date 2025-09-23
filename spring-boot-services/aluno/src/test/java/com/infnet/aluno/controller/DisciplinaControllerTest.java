package com.infnet.aluno.controller;

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
