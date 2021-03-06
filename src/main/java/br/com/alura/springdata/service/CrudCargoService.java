package br.com.alura.springdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.repository.CargoRepository;

@Service
public class CrudCargoService {
    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    private boolean system = true;

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de Cargo deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Remover");
            System.out.println("4 - Listar");

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    remover(scanner);
                    break;
                case 4:
                    listarTodos();
                    break;
                default:
                    system = false;
                    break;
            }

        }
    }

    public void listarTodos() {
        System.out.println("-------------------------------");
        cargoRepository.findAll().iterator().forEachRemaining(System.out::println);
    //    cargoRepository.listarIdDescricaoCargos().forEach(System.out::println);
        System.out.println("-------------------------------");
    }

    public void atualizar(Scanner scanner) {
        listarTodos();
        scanner.useDelimiter("\n");
        System.out.println("Informe o ID que deseja editar:");
        Integer id = scanner.nextInt();
        System.out.println("Informe a nova descricao:");
        String novaDescricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(novaDescricao);
        cargoRepository.save(cargo);
        listarTodos();

    }

    public void remover(Scanner scanner) {
        listarTodos();
        System.out.println("Informe o ID que deseja remover:");
        Integer id = scanner.nextInt();
        Cargo cargo = cargoRepository.findById(id).get();
        cargoRepository.delete(cargo);
        listarTodos();

    }

    private void salvar(Scanner scanner) {
        System.out.println("Descrição do Cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo(descricao);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Salvo");
        listarTodos();
    }
}
