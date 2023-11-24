import java.util.Scanner;

public class Gerente {
    public static void main(String args[]) {
        int opcao;

        System.out.println("------------- Menu do Gerente -----------\n" +
                "1 - Cadastrar novo veículo\n");

        Scanner sc = new Scanner(System.in);
        opcao = Integer.parseInt(sc.nextLine());

        switch (opcao) {
            case 1: // Cadastrar novo veiculo
                String placa;
                boolean placaValida = false;

                do {
                    System.out.println("Digite a placa do veículo: ");
                    placa = sc.nextLine();



                    // Verificar se a placa já existe ou se está no formato correto
                    if (!VerificaDados.validarFormatoPlaca(placa)) {
                        System.out.println("Placa inválida. Deve seguir o padrão LLLNLNN. Tente novamente.");
                    } else if (VerificaDados.verificarPlacaExistente(placa)) {
                        System.out.println("Placa já registrada. Tente novamente.");
                    } else {
                        placaValida = true;
                    }
                } while (!placaValida);

                System.out.println("Digite a marca do veículo: ");
                String marca = sc.nextLine();
                System.out.println("Digite o modelo do veículo: ");
                String modelo = sc.nextLine();
                System.out.println("Digite a cor do veículo: ");
                String cor = sc.nextLine();
                System.out.println("Digite o ano do veículo: ");
                int ano = Integer.parseInt(sc.nextLine());
                
                String grupo;
                boolean grupoValido = false;
                do {
                    System.out.println("Digite o grupo do veículo: ");
                    grupo = sc.nextLine().toLowerCase(); // deixa em minusculo, visto que a verificacao de grupos está considerando nomes em letras minúsculas.

                    // Verifica se o grupo digitado é válido
                    if (!VerificaDados.validarGrupo(grupo)) {
                        System.out.println("[ERRO] " + grupo + " não é um grupo válido.");
                        System.out.println("Tente novamente. Os grupos válidos são: " + "\n" +
                                            "-> basico" + "\n" +
                                            "-> padrao" + "\n" +
                                            "-> premium");
                    } else {
                        grupoValido = true;
                    }

                } while (!grupoValido);

                

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
