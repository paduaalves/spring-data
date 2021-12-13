package br.com.alura.springdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.UnidadeTrabalho;
import br.com.alura.springdata.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
    private final UnidadeTrabalhoRepository unidadetrabalhoRepository;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadetrabalhoRepository) {
        this.unidadetrabalhoRepository = unidadetrabalhoRepository;
    }

    private boolean system = true;

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de Unidade Trabalho deseja executar?");
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
        unidadetrabalhoRepository.findAll().iterator().forEachRemaining(System.out::println);
        System.out.println("-------------------------------");
    }

    public void atualizar(Scanner scanner) {
        listarTodos();
        scanner.useDelimiter("\n");
        System.out.println("Informe o ID que deseja editar:");
        Integer id = scanner.nextInt();
        System.out.println("Informe a nova descricao:");
        String novaDescricao = scanner.next();
        System.out.println("Informe o Endereço da Unidade: ");
        String endereco = scanner.next();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(novaDescricao);
        unidadeTrabalho.setEndereco(endereco);
        unidadetrabalhoRepository.save(unidadeTrabalho);
        listarTodos();

    }

    public void remover(Scanner scanner) {
        listarTodos();
        System.out.println("Informe o ID que deseja remover:");
        Integer id = scanner.nextInt();
        unidadetrabalhoRepository.deleteById(id);
        listarTodos();
    }

    private void salvar(Scanner scanner) {
        scanner.useDelimiter("\n");
        System.out.println("Informe a Descrição do Unidade: ");
        String descricao = scanner.next();
        System.out.println("Informe o Endereço da Unidade: ");
        String endereco = scanner.next();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho(descricao, endereco);
        unidadeTrabalho.setDescricao(descricao);
        unidadetrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Salvo");

    }
}
