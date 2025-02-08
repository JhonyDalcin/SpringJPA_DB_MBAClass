package com.utfpr.backendfuncionariodepartamentosi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "departamento")
@Data
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_departamento", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

}
