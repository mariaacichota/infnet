package com.infnet.aluno.controller;

@WebMvcTest(MatriculaController.class)
class MatriculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatriculaService matriculaService;

    @Test
    void deveMatricularAluno() throws Exception {
        Matricula matricula = new Matricula();
        matricula.setId(1L);

        when(matriculaService.matricular(1L, 1L)).thenReturn(matricula);

        mockMvc.perform(post("/matriculas/1/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}
