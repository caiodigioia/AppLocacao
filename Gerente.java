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
                "3 - Relatórios\n" +
                "4 - Alterar parâmetros de funcionamento");

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
                int ano = sc.nextInt();
                sc.nextLine();  // Consumir a quebra de linha pendente após a leitura do ano

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

                case 4: // Alterar parâmetros de funcionamento da loja
                    listarParametros();
                    System.out.println();
                    System.out.println("Deseja alterar os parâmetros de qual grupo?");
                    String escolhaGrupo = sc.nextLine().toLowerCase();

                     while (!escolhaGrupo.equals("basico") && !escolhaGrupo.equals("padrao") &&!escolhaGrupo.equals("premium")) {
                                    System.out.println("[ERRO] O grupo " + "'" + escolhaGrupo + "'" +  " não é válido. Os grupos válidos são: " + "\n" +
                                            "-> basico" + "\n" +
                                            "-> padrao" + "\n" +
                                            "-> premium" + "\n");

                                    escolhaGrupo = sc.nextLine().toLowerCase();
                                }

                    System.out.println("\n## Grupo " + escolhaGrupo + " selecionado com sucesso.");
                    System.out.println("Deseja alterar qual parâmetro?" + "\n" + 
                                       "1 - Valor da diária" + "\n" +
                                       "2 - Valor do tanque" + "\n" +
                                       "3 - Valor da limpza externa" + "\n" + 
                                       "4 - Valor da limpeza interna" + "\n" +
                                       "5 - Diária do seguro");
                    int escolhaParametro = Integer.parseInt(sc.nextLine());
                    String escolhaParametroNome = null;

                    while (escolhaParametro < 1 || escolhaParametro > 5) {
                                    System.out.println("[Erro] Opção inválida. Utilize uma das seguintes opções: " + "\n" +
                                       "1 - Valor da diária" + "\n" +
                                       "2 - Valor do tanque" + "\n" +
                                       "3 - Valor da limpza externa" + "\n" + 
                                       "4 - Valor da limpeza interna" + "\n" +
                                       "5 - Diária do seguro");

                                    escolhaParametro = Integer.parseInt(sc.nextLine());
                                }

                    // converte a opcao numerica para o nome do campo

                    switch (escolhaParametro) {
                        case 1:
                            escolhaParametroNome = "Valor da diária";
                            break;
                        case 2:
                            escolhaParametroNome = "Valor do tanque";
                            break;
                        case 3:
                            escolhaParametroNome = "Valor da limpza externa";
                            break;
                        case 4:
                            escolhaParametroNome = "Valor da limpeza interna";
                            break;
                        case 5:
                            escolhaParametroNome = "Diária do seguro";
                            break;
                    }

                    int novoParametro;

                    if (escolhaParametroNome != null) {
                        System.out.println("Qual deve ser o novo valor de " + escolhaParametroNome + " ?");
                    }
                    novoParametro = Integer.parseInt(sc.nextLine());

                    alteraParametros(escolhaGrupo, escolhaParametroNome, novoParametro);




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

    private static void listarParametros() {
        String nomeArquivo = "parametros.txt";
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

                System.out.println("------------- Parâmetros Operacionais -----------");

            while ((linha = leitor.readLine()) != null) {
                // Divide a linha usando espaços como delimitador
                String[] partes = linha.split("\\s{2,}");

                if (partes.length >= 2) {
                     System.out.println("Grupo: " + partes[0] + "\n" + "\n -> Valor da diária: R$" + partes[1] + "\n -> Valor do tanque: R$" + partes[2] + "\n -> Valor da limpeza externa: R$" + partes[3] + "\n -> Valor limpeza interna: R$" + partes[4] + "\n -> Diária do seguro: R$" + partes[5] + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void alteraParametros(String escolhaGrupo, String escolhaParametroNome, int novoValor) {
    String nomeArquivo = "parametros.txt";
    String nomeTemporario = "temp.txt";

    try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
         BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeTemporario))) {

        String linha;

        while ((linha = leitor.readLine()) != null) {
            // Divide a linha usando espaços como delimitador
            String[] partes = linha.split("\\s+");

            if (partes.length >= 2 && partes[0].equals(escolhaGrupo)) {
                // Encontra o índice do parâmetro pelo nome
                int indiceParametro = obterIndiceParametro(escolhaParametroNome);

                if (indiceParametro != -1) {
                    // Altera o valor desejado
                    partes[indiceParametro] = Integer.toString(novoValor);
                } else {
                    System.out.println("Parâmetro inválido: " + escolhaParametroNome);
                }
            }

            // Reescreve a linha no arquivo temporário
            escritor.write(String.join("\t", partes));
            escritor.newLine();
        }

    } catch (IOException e) {
        System.err.println("Erro ao ler ou escrever no arquivo: " + e.getMessage());
    }

    // Renomeia o arquivo temporário para substituir o original
    File arquivoOriginal = new File(nomeArquivo);
    File arquivoTemporario = new File(nomeTemporario);
    if (arquivoTemporario.renameTo(arquivoOriginal)) {
        System.out.println("Parâmetro " + escolhaParametroNome + " alterado com sucesso para: R$" + novoValor);
    } else {
        System.err.println("Erro ao renomear o arquivo temporário.");
    }
}

private static int obterIndiceParametro(String parametroNome) {
    switch (parametroNome) {
        case "Valor da diária":
            return 1;
        case "Valor do tanque":
            return 2;
        case "Valor da limpza externa":
            return 3;
        case "Valor da limpeza interna":
            return 4;
        case "Diária do seguro":
            return 5;
        default:
            return -1; // Retorna -1 se o nome do parâmetro for inválido
    }
}


}
