package com.utfpr.backendfuncionariodepartamentosi;

import com.utfpr.backendfuncionariodepartamentosi.entity.Departamento;
import com.utfpr.backendfuncionariodepartamentosi.entity.Funcionario;
import com.utfpr.backendfuncionariodepartamentosi.service.DepartamentoService;
import com.utfpr.backendfuncionariodepartamentosi.service.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendCategoriaMusicaSiApplication {

	private static final Logger log = LoggerFactory.getLogger(BackendCategoriaMusicaSiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendCategoriaMusicaSiApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(DepartamentoService departamentoService, FuncionarioService funcionarioService) {
		return (args) -> {
			log.info("=============Primeiro Funcionário Com Maior Salário");
			log.info(funcionarioService.primeiroFuncionarioComMaiorSalario().toString());
			log.info("=============Busca por nome e quantidade de funcionários:");
			log.info(funcionarioService.buscarFuncionarioPorNomeEqtdeDependents("Ana Clara", 2).toString());
			log.info("=============Primeiro Departamento Cadastrado:");
			log.info(departamentoService.buscarPrimeiroDepartamentoCadastrado().toString());
			log.info("=============Funcionários 3 Maiores Salários:");
			funcionarioService.funcionariosCom3MaioresSalarios().forEach(System.out::println);
			log.info("=============Funcionários pelo Nome do Departamento JPQL:");
			funcionarioService.buscarFuncionarioPorNomeDepartamento("Diretoria").forEach(System.out::println);
			log.info("=============Funcionários sem dependentes por ordem alfabética JPQL:");
			funcionarioService.funcionariosSemDependentesPorOrdemAlfabetica().forEach(System.out::println);
			log.info("=============Funcionários com salário maior que JPQL sobrescrevento palavra-chave:");
			funcionarioService.funcionariosComSalarioMaiorQue(8000.00).forEach(System.out::println);
			log.info("=============Funcionários com salário maior que Native Query:");
			funcionarioService.nativeQueryFuncionariosComSalarioMaiorQue(8000.00).forEach(System.out::println);
			log.info("=============Funcionários com númerode de dependentes por NamedQuery:");
			funcionarioService.namedFuncionariosPorQntdeDependentes(2).forEach(System.out::println);
			log.info("=============Funcionários que contem nome por NamedNativeQuery:");
			funcionarioService.namedFuncionariosContemNome("Silva").forEach(System.out::println);

			log.info("=============Salários antes da procedure: ");
			for (Funcionario f : funcionarioService.listarTodosFuncionarios())
				log.info(f.toString());
			log.info("=============Chamando PROCEDURE para aumentar salário em 15%");
			funcionarioService.salaryIncrease(15);
			for (Funcionario f : funcionarioService.listarTodosFuncionarios())
				log.info(f.toString());

			log.info("=============Funcionários sem dependentes da Diretoria");
			for (Funcionario f : funcionarioService.buscarFuncionarioSemDependentesPorDepartamento("Diretoria"))
				log.info(f.toString());

			log.info("=============Atualizando Recrusos Humanos para Financeiro");
			int affectedFunc = funcionarioService.atualizarDepartamento("Financeiro", "Recursos Humanos");
			log.info("Impacted Employees: " + affectedFunc);
			for (Funcionario f : funcionarioService.listarTodosFuncionarios())
				log.info(f.toString());

			log.info("=============Deletando todos os funcionários da Diretoria");
			int deletedFunc = funcionarioService.deletarTodosFuncionariosPorDepartamento("Diretoria");
			log.info("Deleted Employees: " + deletedFunc);
			for (Funcionario f : funcionarioService.listarTodosFuncionarios())
				log.info(f.toString());

			log.info("=============Salvando novo funcionário com novo departamento");
			Departamento departamento = new Departamento("Serviços Gerais");
			Funcionario funcionario = new Funcionario("José da Silva", 3, 3500.00, "Severino");
			departamentoService.save(departamento, funcionario);
			for (Funcionario f : funcionarioService.listarTodosFuncionarios())
				log.info(f.toString());
		};
	}


}
