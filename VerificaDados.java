import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VerificaDados {

    public static boolean verificarPlacaExistente(String placa) {
        String nomeArquivo = "veiculos.txt";

        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = leitor.readLine()) != null) {
                // Divide a linha usando espaços como delimitador
                String[] partes = linha.split("\\s+");

                // A placa está na primeira coluna
                String placaExistente = partes[0];

                // Verifica se a placa existe
                if (placaExistente.equals(placa)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        

        // A placa não foi encontrada no arquivo
        return false;
    }

    public static boolean validarFormatoPlaca(String placa) {
        return placa.matches("[A-Z]{3}\\d[A-Z]\\d{2}");
    }

    public static boolean validarGrupo (String grupo) {
        if (grupo.equalsIgnoreCase("basico") || grupo.equalsIgnoreCase("padrao") || grupo.equalsIgnoreCase("premium")) {
            return true;
        }

        else {
            return false;
        }
    }

    public static boolean validaExclusao(String placa) {
    String nomeArquivo = "veiculos.txt";
    try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
        String linha;

        while ((linha = leitor.readLine()) != null) {
            // Divide a linha usando espaços como delimitador
            String[] partes = linha.split("\\s+");

            // A placa está na primeira coluna
            String placaCarro = partes[0];

            // O status está na sétima coluna
            String statusCarro = partes[6];

            // Verifica se a placa existe
            if (placaCarro.equals(placa)) {
                if (statusCarro.equals("locado")) {
                    System.out.println("Não é possível excluir um veículo locado. Para excluí-lo, é necessário devolvê-lo.");
                    return false;
                } else {
                    // A placa foi encontrada e o veículo não está locado
                    return true;
                }
            }
        }

        // Se chegou aqui, a placa não foi encontrada
        System.out.println("Placa não encontrada no arquivo.");
        return false;
    } catch (IOException e) {
        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        return false; // Tratar o erro de leitura do arquivo
    }
}

}