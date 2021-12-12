package br.com.alura.springdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.repository.CargoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CrudCargoService {
    private final CargoRepository cargoRepository;

    public void inicial(Scanner scanner) {
        salvar(scanner);
    }

    public void listarTodos(){
        System.out.println("-------------------------------");
        cargoRepository.findAll().iterator().forEachRemaining(System.out::println);
        System.out.println("-------------------------------");
    }

    public void editar(Scanner scanner){
        listarTodos();
        scanner.useDelimiter("\n");
        System.out.println("Informe o ID que pretende editar:");
        Integer id = scanner.nextInt();
        System.out.println("Informe a nova descricao:");
        String novaDescricao = scanner.next();
        Cargo cargo = cargoRepository.findById(id).get();
        cargo.setDescricao(novaDescricao);
        cargoRepository.save(cargo);
        listarTodos();

    }

    private void salvar(Scanner scanner) {
        System.out.println("Descrição do Cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo(descricao);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Salvo");
    }
}
