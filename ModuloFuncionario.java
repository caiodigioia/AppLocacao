import java.util.Scanner;

public class ModuloFuncionario {

  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);
  
    Cliente[] clientes = new Cliente[10];
    int numClientes = 0;

    String opt;
    do {
      mainMenu();
      System.out.print("-> ");
      opt = scan.nextLine();

      if(opt.equals("cliente")) {
        System.out.print("Nome Completo: ");
        String nome = scan.nextLine();

        System.out.print("CPF: ");
        String cpf = scan.nextLine();

        System.out.print("Data de Nascimento (YYYY-MM-DD): ");
        String dataNascimento = scan.nextLine();

        System.out.print("Email: ");
        String email = scan.nextLine();

        System.out.print("Telefone/Celular: ");
        String celular = scan.nextLine();

        clientes[numClientes] = new Cliente(cpf, nome, dataNascimento, email, celular);
        numClientes++;
      }
      else  if(opt.equals("listar")) {
        System.out.println("## Clientes ##");
        for(int i = 0; i < numClientes; i++) {
          System.out.println(clientes[i]);
        }
      }
      else if(opt.equals("sair")) {
        System.out.println("Encerrando sistema.");
      }
      else {
        System.err.println("Erro: comando desconhecido!");
      }
    } while(!opt.equals("sair"));
  }

  public static void mainMenu() {
    System.out.println("--| Modulo do Funcionario |--\n");
    System.out.println("<cliente> Cadastrar novo cliente");
    System.out.println("<listar> Exibe os cilentes cadastrados");
    System.out.println("<sair> Encerra o sistema\n");
  }
}
