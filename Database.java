import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Database {

  // Atributos
  private static final String clientesFile = "clientes.txt";

  // Métodos
  public static Cliente getCliente(String cpf) {
    try(BufferedReader reader = new BufferedReader(new FileReader(clientesFile))) {
      String linha;

      while((linha = reader.readLine()) != null) {
        // Divide a linha usando tabs como delimitador
        String[] partes = linha.split("\\t");

        // O CPF está na primeira coluna
        String cpfExistente = partes[0];

        // Verifica se o CPF existe e retorna o cliente
        if(cpfExistente.equals(cpf)) {
          Cliente cliente = new Cliente(partes[0], partes[1], partes[2], partes[3], partes[4]);
          return cliente;
        }
      }
    }
    catch(IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e);
    }

    // O CPF não foi encontrado no arquivo
    return null;
  }

  public static void adicionarCliente(Cliente cliente) {
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(clientesFile, true))) {
      writer.write(cliente.toString());
      System.out.println("Cliente adicionado com sucesso.");
    }
    catch(IOException e) {
      System.err.println("Erro ao adicionar ao arquivo: " + e);
    }
  }

  public static void listarClientes() {
    try(BufferedReader reader = new BufferedReader(new FileReader(clientesFile))) {
      String linha;

      System.out.println("## Clientes ##");
      while((linha = reader.readLine()) != null) {
        // Divide a linha usando tabs como delimitador
        String[] partes = linha.split("\\t");

        System.out.println(String.format("%s\t%s\t%s\t%s\t%s",
          partes[0], partes[1], partes[2], partes[3], partes[4])
        );
      }
    }
    catch(IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e);
    }
  }
}
