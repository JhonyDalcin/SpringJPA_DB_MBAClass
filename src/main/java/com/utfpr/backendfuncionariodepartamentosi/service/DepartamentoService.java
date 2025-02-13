package com.utfpr.backendfuncionariodepartamentosi.service;

import com.utfpr.backendfuncionariodepartamentosi.entity.Departamento;
import com.utfpr.backendfuncionariodepartamentosi.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    final private DepartamentoRepository repository;

    public DepartamentoService(DepartamentoRepository repository) {
        this.repository = repository;
    }

    public List<Departamento> listarTodosDepartamentos() {
        return repository.findAll();
    }

    public Departamento saveDepartamento(Departamento departamento) {return repository.save(departamento);}

    public Departamento buscarPrimeiroDepartamentoCadastrado() {return repository.findFirstByOrderByIdAsc();}

}
