import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Gerente {
    public static void main(String args[]) {
        int opcao;
        String placa;

        System.out.println("------------- Menu do Gerente -----------\n" +
                "1 - Cadastrar novo veículo\n" +
                "2 - Excluir veículo\n" +
                "3 - Relatórios");

        Scanner sc = new Scanner(System.in);
        opcao = Integer.parseInt(sc.nextLine());

        switch (opcao) {
            case 1: // Cadastrar novo veículo
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
<<<<<<< HEAD
                int ano = sc.nextInt();
                sc.nextLine();  // Consumir a quebra de linha pendente após a leitura do ano

=======
                int ano = Integer.parseInt(sc.nextLine());
                
>>>>>>> 8f350a4482232241a992c3550875c8c6d590d257
                String grupo;
                boolean grupoValido = false;
                do {
                    System.out.println("Digite o grupo do veículo: ");
                    grupo = sc.nextLine().toLowerCase();

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

            case 2: // Excluir veículo
                System.out.println("Para listar todos os veículos, envie uma placa vazia.");

                do {
                    System.out.println("Digite a placa do veículo que deseja excluir: ");
                    placa = sc.nextLine();

                    if (placa.equals("")) {
                        listarVeiculos();
                    }
                } while (placa.equals(""));

                // Verificações se a placa existe e se o veículo não está locado
                if (VerificaDados.validaExclusao(placa)) {
                    String motivo;
                    System.out.println("Digite o motivo da exclusão: ");
                    motivo = sc.nextLine();

                    excluirVeiculo(placa, motivo);
                }
                break;

            case 3: // Gerar relatórios
                int opcao_relatorio;

                do {
                    System.out.println("Escolha uma opção de relatório: " + "\n" +
                            "1 - Clientes" + "\n" +
                            "2 - Veículos" + "\n" +
                            "3 - Locações/Reservas" + "\n" +
                            "4 - Voltar");

                    opcao_relatorio = Integer.parseInt(sc.nextLine());

                } while (opcao_relatorio != 1 && opcao_relatorio != 2 && opcao_relatorio != 3 && opcao_relatorio != 4);

                switch (opcao_relatorio) {
                    case 1: // Relatório de Clientes
                        System.out.println("------------- Relatório de Clientes -----------\n");
                        listarClientes();
                        break;
                    case 2: // Relatório de veículos
                        System.out.println("------------- Relatório de Veículos -----------\n");
                        listarVeiculos();
                        break;
                    case 3: // Relatório de reservas
                        System.out.println("------------- Relatório de Reservas -----------\n");
                        System.out.println("Lista de reservas:");
                        System.out.println("Deseja fazer algum filtro por status? ");
                        int opcao_filtro;

                        do {
                            System.out.println("1 - Sim" + "\n" +
                                    "2 - Não");

                            opcao_filtro = Integer.parseInt(sc.nextLine());
                        } while (opcao_filtro != 1 && opcao_filtro != 2);

                        switch (opcao_filtro) {
                            case 1: // Sim
                                String escolhaOpcaoFiltro = null;

                                do {
                                    System.out.println("Deseja filtrar por qual status?" + "\n" +
                                            "-> concluida" + "\n" +
                                            "-> ativa" + "\n" +
                                            "-> cancelada" + "\n" +
                                            "-> no-show" + "\n" +
                                            "-> efetivada");

                                    escolhaOpcaoFiltro = sc.nextLine();
                                } while (!escolhaOpcaoFiltro.equals("concluida") && !escolhaOpcaoFiltro.equals("ativa") &&
                                        !escolhaOpcaoFiltro.equals("cancelada") && !escolhaOpcaoFiltro.equals("no-show") &&
                                        !escolhaOpcaoFiltro.equals("efetivada"));

                                listarReservasPorStatus(escolhaOpcaoFiltro);
                                break;

                            case 2: // Não
                                listarReservas();
                                break;
                        }

                        break;

                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    // Função para listar todos os veículos
    private static void listarVeiculos() {
    String nomeArquivo = "veiculos.txt";
    try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
        String linha;

        System.out.println("Lista de todos os veículos:");

        while ((linha = leitor.readLine()) != null) {
            // Divide a linha usando espaços como delimitador
            String[] partes = linha.split("  +");
            
            // Adiciona uma verificação para garantir que há 7 elementos em partes
            if (partes.length == 7) {
                System.out.println("Placa: " + partes[0] + ", Marca: " + partes[1] + ", Modelo: " + partes[2] + ", Cor: " + partes[3] + ", Ano: " + partes[4] + ", Grupo: " + partes[5] + ", Status: " + partes[6]);
            } else if (partes.length == 9) { // para veículos indisponíveis, já que tem o motivo da indisponibilidade
                System.out.println("Placa: " + partes[0] + ", Marca: " + partes[1] + ", Modelo: " + partes[2] + ", Cor: " + partes[3] + ", Ano: " + partes[4] + ", Grupo: " + partes[5] + ", Status: " + partes[6]+ " - " + partes[8]) ;
            }
            
             else {
                System.err.println("Erro ao processar a linha: " + linha);
            }
        }
    } catch (IOException e) {
        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    }
}

    private static void listarClientes() {
    String nomeArquivo = "clientes.txt";
    try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
        String linha;

        System.out.println("Lista de clientes:");

        while ((linha = leitor.readLine()) != null) {
            try {
                // Divide a linha usando espaços como delimitador
                String[] partes = linha.split("\\s{2,}");

                System.out.println("CPF: " + partes[0] + ", nome: " + partes[1] + ", data de nascimento: " + partes[2] + ", e-mail: " + partes[3] + ", celular: " + partes[4]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Erro ao processar a linha: " + linha);
            }
        }
    } catch (IOException e) {
        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    }
}


    private static void listarReservas() {
        String nomeArquivo = "reservas.txt";
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = leitor.readLine()) != null) {
                // Divide a linha usando espaços como delimitador
                String[] partes = linha.split("\\s{2,}");

                System.out.println("ID: " + partes[0] + ", CPF do Cliente: " + partes[1] + ", placa: " + partes[2] + ", Data de retirada: " + partes[3] + ", Data de devolução: " + partes[4] + ", Valor da locação: R$" + partes[5] + ", Status: " + partes[6]);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void listarReservasPorStatus(String escolhaOpcaoFiltro) {
        String nomeArquivo = "reservas.txt";
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = leitor.readLine()) != null) {
                // Divide a linha usando espaços como delimitador
                String[] partes = linha.split("\\s{2,}");

                
                if (partes[6].equals(escolhaOpcaoFiltro)) {
                    System.out.println("ID: " + partes[0] + ", CPF do Cliente: " + partes[1] + ", placa: " + partes[2] + ", Data de retirada: " + partes[3] + ", Data de devolução: " + partes[4] + ", Valor da locação: R$" + partes[5] + ", Status: " + partes[6]);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void excluirVeiculo(String placa, String motivo) {
        String nomeArquivo = "veiculos.txt";
        File arquivoOriginal = new File(nomeArquivo);
        File arquivoTemporario = new File("veiculos_temp.txt");

       try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoOriginal));
     BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoTemporario))) {

    String linha;

    while ((linha = leitor.readLine()) != null) {
        // Divide a linha usando espaços como delimitador
        String[] partes = linha.split("\\s+");

        // Se a placa do veículo coincide, atualiza o status
        if (partes[0].equals(placa)) {

            linha = String.format("%-10s%-10s%-15s%-15s%-10s%-10s%-15s",
                    partes[0], partes[1], partes[2], partes[3], partes[4], partes[5], "Indisponível - " + motivo);
        }

        // Verifica se a linha não está vazia antes de escrever no arquivo
        if (!linha.trim().isEmpty()) {
            escritor.write(linha);
            escritor.newLine();  // Adiciona uma quebra de linha ao final de cada linha válida
        }
    }

} catch (IOException e) {
    System.err.println("Erro ao ler ou escrever no arquivo: " + e.getMessage());
}

        // Renomeia o arquivo temporário para o original
        if (arquivoTemporario.renameTo(arquivoOriginal)) {
            System.out.println("Veículo excluído pelo motivo " + "-> " + motivo);
        } else {
            System.err.println("Erro ao excluir o veículo.");
        }
    }

}
