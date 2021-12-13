package br.com.alura.springdata.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.springdata.orm.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer>{
    @Query("select new Cargo (c.id, c.descricao) from Cargo c")
    public List<Cargo> listarIdDescricaoCargos();
    
}