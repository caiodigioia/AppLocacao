import java.util.Scanner;

public class ModuloFuncionario {

  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);
    String opt;

    do {
      mainMenu();
      System.out.print("-> ");
      opt = scan.nextLine();

      switch(opt) {
        case "cliente":
          try {
            cadastroCliente(scan);
          }
          catch(ClienteException e) {
            System.err.println("Erro: " + e);
          }
          break;

        case "listar":
          Database.listarClientes();
          break;

        case "sair":
          System.out.println("Encerrando sistema.");
          break;

        default:
          System.err.println("Erro: comando desconhecido!");
          break;
      }
    } while(!opt.equals("sair"));
  }

  public static void mainMenu() {
    System.out.println("--| Modulo do Funcionario |--\n");
    System.out.println("<cliente> Cadastrar novo cliente");
    System.out.println("<listar> Exibe os cilentes cadastrados");
    System.out.println("<sair> Encerra o sistema\n");
  }

  public static void cadastroCliente(Scanner scan) throws ClienteException {
    System.out.print("Nome Completo: ");
    String nome = scan.nextLine();
    if(ClienteException.nomeInvalido(nome)) {
      throw new ClienteException("Nome invalido.");
    }

    System.out.print("CPF: ");
    String cpf = scan.nextLine();
    if(ClienteException.cpfInvalido(cpf)) {
      throw new ClienteException("CPF invalido.");
    }
    if(ClienteException.clienteExistente(cpf)) {
      throw new ClienteException("Cliente ja cadastrado.");
    }

    System.out.print("Data de Nascimento (YYYY-MM-DD): ");
    String dataNascimento = scan.nextLine();
    if(ClienteException.dataNascimentoInvalida(dataNascimento)) {
      throw new ClienteException("Data de nascimento invalida.");
    }
    if(ClienteException.menorDeIdade(dataNascimento)) {
      throw new ClienteException("Cliente menor de idade.");
    }

    System.out.print("Email: ");
    String email = scan.nextLine();
    if(ClienteException.emailInvalido(email)) {
      throw new ClienteException("Email invalido.");
    }

    System.out.print("Telefone/Celular (XX XXXX-XXXX): ");
    String celular = scan.nextLine();
    if(ClienteException.celularInvalido(celular)) {
      throw new ClienteException("Telefone/Celular invalido.");
    }

    if(ClienteException.dadosNaoPreenchidos(nome, cpf, dataNascimento, email, celular)) {
      throw new ClienteException("Dados obrigatorios nao informados.");
    }

    Cliente novoCliente = new Cliente(cpf, nome, dataNascimento, email, celular);
    Database.adicionarCliente(novoCliente);
  }
}
