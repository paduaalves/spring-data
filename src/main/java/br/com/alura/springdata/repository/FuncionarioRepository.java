package br.com.alura.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.springdata.orm.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
    @Query("select f from Funcionario f join fetch f.cargo")
    public List<Funcionario> listarFuncionarioComCargo();
}