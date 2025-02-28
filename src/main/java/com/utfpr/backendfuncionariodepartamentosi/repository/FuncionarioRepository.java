package com.utfpr.backendfuncionariodepartamentosi.repository;

import com.utfpr.backendfuncionariodepartamentosi.entity.Departamento;
import com.utfpr.backendfuncionariodepartamentosi.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

//  1. Listar um funcionário pelo seu nome e quantidade de dependentes utilizando consulta por palavras-chaves.
    Funcionario findByNomeAndQtdeDependentes(String nome, int qtdeDependentes);

//  2. Listar todos os funcionários de um determinado departamento por JPQL via @Query.
    @Query("select f from Funcionario f where f.departamento.id = (select d.id from Departamento d where d.nome = ?1)")
    List<Funcionario> findFuncionarioByNomeDoDepartamento(String name);

//  4.Listar o primeiro funcionário que tem o maior salário.
    Funcionario findFirstByOrderBySalarioDesc();

//  5. Listar os 3 (três) primeiros funcionários que tem os maiores salários.
    List<Funcionario> findFirst3ByOrderBySalarioDesc();

//  6. Listar os funcionários que não tem dependentes em ordem crescente de nome por JPQL via @Query.
    @Query("select f from Funcionario f where f.qtdeDependentes = 0 order by f.nome asc")
    List<Funcionario> findFuncionariosSemDependentesPorOrdemAlfabetica();

//  7. Listar os funcionários que tem salário maior que um determinado valor por JPQL sobrescrevendo palavras-chaves @Query.
    @Query("select f from Funcionario f where f.salario > ?1")
    List<Funcionario> findFuncionariosBySalarioIsGreaterThan(double salario);

//  8. Listar os funcionários que tem salário maior que um determinado valor por @Query com native query.
    @Query(value = "select * from funcionario where funcionario.salario > ?1 ", nativeQuery = true)
    List<Funcionario> nativeQueryFindFuncionariosComSalarioMaiorQue(double salario);

//  11. Alterar a interface FuncionarioRepository para utilizar a @NamedQuery e @NamedNativeQuery realizadas nas questões 9 e 10 (respectivamente).

    @Query(name = "Funcionario.byQtdeDependentes")
    List<Funcionario> findFuncionariosByQtdeDependentes(int qtdeDependentes);

    @Query(name = "Funcionario.byContemNome")
    List<Funcionario> findFuncionariosByContemNome(String nome);

//  1. Implementar a chamada de uma stored procedure criada no banco de dados, que aumenta o salário de todos os funcionários em X porcento, onde X é um número inteiro.
    @Procedure("proc_salary_increase")
    void procSalaryIncrease(int porcentage);

//  2. Uma consulta que lista todos os funcionários de um determinado departamento que não possuam dependentes utilizando parâmetros nomeados.

    @Query(value = "select * from funcionario where cod_departamento = (select cod_departamento from departamento where nome = :departamento) and qtde_dependentes = 0", nativeQuery = true)
    List<Funcionario> findFuncionariosSemDependentesByDepartamento(@Param("departamento") String departamento);

//  3. Uma instrução de update que troca todos os funcionários de um determinado departamento para outro departamento utilizando a anotação @Modifying.

    @Transactional
    @Modifying
    @Query("update Funcionario f set f.departamento = (select d from Departamento d where d.nome = :newDepartment) where f.departamento = (select d from Departamento d where d.nome = :oldDepartment)")
    int updateDepartamento(@Param("newDepartment") String newDepartment, @Param("oldDepartment") String oldDepartment);

//  4. Uma instrução de delete que exclui todos os funcionários de um determinado departamento utilizando a anotação @Modifying.

    @Transactional
    @Modifying
    @Query("delete Funcionario f where f.departamento = (select d from Departamento d where d.nome = :department)")
    int deleteTodosFuncionariosPorDepartamento(@Param("department") String department);
}