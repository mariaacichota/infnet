package com.infnet.aluno.model;

@Entity
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String codigo;

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private List<Matricula> matriculas = new ArrayList<>();
}