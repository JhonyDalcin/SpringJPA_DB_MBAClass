package com.utfpr.backendfuncionariodepartamentosi.service;

import com.utfpr.backendfuncionariodepartamentosi.entity.Departamento;
import com.utfpr.backendfuncionariodepartamentosi.entity.Funcionario;
import com.utfpr.backendfuncionariodepartamentosi.repository.DepartamentoRepository;
import com.utfpr.backendfuncionariodepartamentosi.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartamentoService {

    final private DepartamentoRepository repository;
    final private FuncionarioRepository funcionarioRepository;

    public DepartamentoService(DepartamentoRepository repository, FuncionarioRepository funcionarioRepository) {
        this.repository = repository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Departamento> listarTodosDepartamentos() {
        return repository.findAll();
    }

    public Departamento saveDepartamento(Departamento departamento) {return repository.save(departamento);}

    public Departamento buscarPrimeiroDepartamentoCadastrado() {return repository.findFirstByOrderByIdAsc();}

//  Exercício 5- Transações
    @Transactional(readOnly = false)
    public void save(Departamento departamento, Funcionario funcionario) {
        repository.save(departamento);
        funcionario.setDepartamento(departamento);
        funcionarioRepository.save(funcionario);
    }

}
