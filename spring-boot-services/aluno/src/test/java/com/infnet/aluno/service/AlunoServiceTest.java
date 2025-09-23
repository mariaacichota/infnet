package com.infnet.aluno.service;

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