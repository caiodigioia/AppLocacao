import java.util.Scanner;

public class Gerente {
    public static void main(String args[]) {
        int opcao;

        System.out.println("------------- Menu do Gerente -----------\n" +
                "1 - Cadastrar novo veículo\n");

        Scanner sc = new Scanner(System.in);
        opcao = sc.nextInt();
        sc.nextLine();  // Consumir a quebra de linha pendente após a leitura da opção

        switch (opcao) {
            case 1: // Cadastrar novo veiculo
                System.out.println("Digite a placa do veículo: ");
                String placa = sc.nextLine();
                System.out.println("Digite a marca do veículo: ");
                String marca = sc.nextLine();
                System.out.println("Digite o modelo do veículo: ");
                String modelo = sc.nextLine();
                System.out.println("Digite a cor do veículo: ");
                String cor = sc.nextLine();
                System.out.println("Digite o ano do veículo: ");
                int ano = sc.nextInt();
                sc.nextLine();  // Consumir a quebra de linha pendente após a leitura do ano
                System.out.println("Digite o grupo do veículo: ");
                String grupo = sc.nextLine();

                // Chama o construtor da classe Carros para criar um novo veículo
                Carros novoVeiculo = new Carros(placa, marca, modelo, cor, ano, grupo);

                // Instancia 'novoVeiculo' agora contém as informações do veículo recém-criado
                System.out.println("Novo veículo cadastrado:\n" + novoVeiculo.toString());
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
}
