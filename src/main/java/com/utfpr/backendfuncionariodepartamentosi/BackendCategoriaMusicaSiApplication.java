package com.utfpr.backendfuncionariodepartamentosi;

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
			log.info("");
			log.info("");
			log.info("=============Listagem de todos os DEPARTAMENTOS:");
			departamentoService.listarTodosDepartamentos().forEach(System.out::println);

			log.info("");
			log.info("");
			log.info("=============Listagem de todos os FUNCIONÁRIOS:");
			funcionarioService.listarTodosFuncionarios().forEach(System.out::println);

		};
	}


}
