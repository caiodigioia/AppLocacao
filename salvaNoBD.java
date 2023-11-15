import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class salvaNoBD {

    public static void escreverNoArquivo(Carros carro) {
        String nomeArquivo = "veiculos.txt";

        try {
            // Cria um objeto FileWriter com o nome do arquivo e o parâmetro para anexar (true)
            FileWriter escritor = new FileWriter(nomeArquivo, true);

            // Cria um BufferedWriter para escrever de forma mais eficiente
            BufferedWriter bufferEscritor = new BufferedWriter(escritor);

            // Escreve as informações do carro no arquivo
            bufferEscritor.write(carro.toString());

            // Fecha o BufferedWriter (isso também fecha o FileWriter)
            bufferEscritor.close();

            System.out.println("Conteúdo foi adicionado ao arquivo com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao adicionar ao arquivo: " + e.getMessage());
        }
    }
}
