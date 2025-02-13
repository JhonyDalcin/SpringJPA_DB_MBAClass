package com.utfpr.backendfuncionariodepartamentosi.repository;

import com.utfpr.backendfuncionariodepartamentosi.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

//    1. Listar um funcionário pelo seu nome e quantidade de dependentes utilizando consulta por palavras-chaves.
    Funcionario findByNomeAndQtdeDependentes(String nome, int qtdeDependentes);

//    4.Listar o primeiro funcionário que tem o maior salário.
    Funcionario findFirstByOrderBySalarioDesc();

//    5. Listar os 3 (três) primeiros funcionários que tem os maiores salários.
    List<Funcionario> findFirst3ByOrderBySalarioDesc();
}
