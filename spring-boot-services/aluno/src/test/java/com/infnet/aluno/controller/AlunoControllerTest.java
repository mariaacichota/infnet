package com.infnet.aluno.controller;

@WebMvcTest(AlunoController.class)
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Test
    void deveCadastrarAluno() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Maria");

        when(alunoService.cadastrar(any(Aluno.class))).thenReturn(aluno);

        mockMvc.perform(post("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Maria\",\"cpf\":\"123\",\"email\":\"maria@email\",\"telefone\":\"999\",\"endereco\":\"Rua A\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Maria"));
    }
}

