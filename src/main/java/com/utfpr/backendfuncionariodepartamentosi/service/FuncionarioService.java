package com.utfpr.backendfuncionariodepartamentosi.service;

import com.utfpr.backendfuncionariodepartamentosi.entity.Funcionario;
import com.utfpr.backendfuncionariodepartamentosi.repository.FuncionarioRepository;
import org.springframework.context.annotation.Bean;
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

}
