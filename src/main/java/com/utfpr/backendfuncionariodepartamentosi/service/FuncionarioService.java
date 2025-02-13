package com.utfpr.backendfuncionariodepartamentosi.service;

import com.utfpr.backendfuncionariodepartamentosi.entity.Funcionario;
import com.utfpr.backendfuncionariodepartamentosi.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    final private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> listarTodosFuncionarios() {
        return repository.findAll();
    }

    public Funcionario save(Funcionario funcionario) {return repository.save(funcionario);}

//    Exercício 1
    public Funcionario buscarFuncionarioPorNomeEqtdeDependents(String nome, int dependentes) {return repository.findByNomeAndQtdeDependentes(nome, dependentes);}

//    Exercício 4
    public Funcionario primeiroFuncionarioComMaiorSalario() {return repository.findFirstByOrderBySalarioDesc();}

//    Exercício 5
    public List<Funcionario> funcionariosCom3MaioresSalarios() {return repository.findFirst3ByOrderBySalarioDesc();}

}
