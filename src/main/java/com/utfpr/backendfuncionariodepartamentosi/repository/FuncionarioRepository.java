package com.utfpr.backendfuncionariodepartamentosi.repository;

import com.utfpr.backendfuncionariodepartamentosi.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

}
