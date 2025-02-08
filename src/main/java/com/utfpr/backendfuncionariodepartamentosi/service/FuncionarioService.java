package com.utfpr.backendfuncionariodepartamentosi.service;

import com.utfpr.backendfuncionariodepartamentosi.entity.Funcionario;
import com.utfpr.backendfuncionariodepartamentosi.repository.FuncionarioRepository;

import java.util.List;

public class FuncionarioService {

    final private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> listarTodosFuncionarios() {
        return repository.findAll();
    }

}
