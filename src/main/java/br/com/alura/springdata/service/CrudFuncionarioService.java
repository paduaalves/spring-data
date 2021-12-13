package br.com.alura.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.UnidadeTrabalho;
import br.com.alura.springdata.repository.FuncionarioRepository;

@Service
public class CrudFuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final CrudCargoService crudCargoService;
    private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;

    private boolean system = true;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CrudCargoService crudCargoService, CrudUnidadeTrabalhoService crudUnidadeTrabalhoService) {
        this.funcionarioRepository = funcionarioRepository;
        this.crudCargoService = crudCargoService;
        this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
    }



    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de Funcionário deseja executar?");
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
        funcionarioRepository.listarFuncionarioComCargo().forEach(System.out::println);
        System.out.println("-------------------------------");
    }

    public void atualizar(Scanner scanner) {

        listarTodos();
        scanner.useDelimiter("\n");
        System.out.println("Informe o ID que deseja editar:");
        Integer id = scanner.nextInt();
        System.out.println("Digite o nome do funcionário");
        String nome = scanner.next();
        System.out.println("Digite o CPF do funcionário");
        String cpf = scanner.next();
        System.out.println("Digite a data de contrtação do funcionário");
        String dataContratacao = scanner.next();
        System.out.println("Digite o ID do cargo");
        crudCargoService.listarTodos();
        int idCargo = scanner.nextInt();
        Cargo cargo = new Cargo();
        cargo.setId(idCargo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Funcionario funcionario = new Funcionario(nome, cpf, LocalDate.parse(dataContratacao, formatter), cargo);
        funcionario.setId(id);
        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
        listarTodos();

    }

    public void remover(Scanner scanner) {
        listarTodos();
        System.out.println("Informe o ID que deseja remover:");
        Integer id = scanner.nextInt();
        Funcionario funcionario = funcionarioRepository.findById(id).get();
        funcionarioRepository.delete(funcionario);
        System.out.println("Removido");
        listarTodos();

    }

    private void salvar(Scanner scanner) {
        scanner.useDelimiter("\n");
        System.out.println("Digite o nome do funcionário");
        String nome = scanner.next();
        System.out.println("Digite o CPF do funcionário");
        String cpf = scanner.next();
        System.out.println("Digite a data de contrtação do funcionário");
        String dataContratacao = scanner.next();
        System.out.println("Digite o ID do cargo");
        crudCargoService.listarTodos();
        int idCargo = scanner.nextInt();
        crudUnidadeTrabalhoService.listarTodos();
        System.out.println("Informe as unidades separadas por hifen");
        String unidades = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setId(idCargo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Funcionario funcionario = new Funcionario(nome, cpf, LocalDate.parse(dataContratacao, formatter), cargo);
        List<String> idsUidades = Arrays.asList(unidades.split("-"));
        idsUidades.forEach(id->funcionario.AdicionarUnidadetrabalho(new UnidadeTrabalho(Integer.valueOf(id))));
        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
        listarTodos();

    }
}
