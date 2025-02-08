package com.utfpr.backendfuncionariodepartamentosi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "funcionario")
@Data
public class Funcionario {

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
