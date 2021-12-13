package br.com.alura.springdata.orm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private LocalDate dataContratacao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "funcionarios_unidades")
    @ToString.Exclude
    private List<UnidadeTrabalho> unidades = new ArrayList<UnidadeTrabalho>();

    public Funcionario(String nome, String cpf, LocalDate dataContratacao, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataContratacao = dataContratacao;
        this.cargo = cargo;
    }
    public void AdicionarUnidadetrabalho(UnidadeTrabalho unidadeTrabalho){
        this.unidades.add(unidadeTrabalho);
    }
}
