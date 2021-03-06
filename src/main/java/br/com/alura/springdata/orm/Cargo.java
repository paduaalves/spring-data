package br.com.alura.springdata.orm;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cargos")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;

    @ToString.Exclude
    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios;

    public Cargo(String nome) {
        this.descricao = nome;
    }

    public Cargo(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

}
