package com.utfpr.backendfuncionariodepartamentosi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "funcionario")
@Data
@NamedQuery(
        name = "Funcionario.byQtdeDependentes",
        query = "from Funcionario f where f.qtdeDependentes = ?1"
)
@NamedNativeQuery(
        name = "Funcionario.byContemNome",
        query = "select * from funcionario f where f.nome like CONCAT('%', ?1, '%')",
        resultClass = Funcionario.class
)
public class Funcionario {

    public Funcionario() {}

    public Funcionario(String nome, Integer qtdeDependentes, Double salario, String cargo) {
        this.nome = nome;
        this.qtdeDependentes = qtdeDependentes;
        this.salario = salario;
        this.cargo = cargo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_funcionario", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "qtde_dependentes", nullable = false)
    private Integer qtdeDependentes;

    @Column(name = "salario", nullable = false)
    private Double salario;

    @Column(name = "cargo", nullable = false)
    private String cargo;

    @ManyToOne
    @JoinColumn(name = "cod_departamento", nullable = false)
    private Departamento departamento;

}
