import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeParseException;

public class ClienteException extends Exception {

  // Construtor
  public ClienteException(String erro) {
    super(erro);
  }

  // Métodos
  public static boolean dadosNaoPreenchidos(String nome, String cpf, String dataNascimento, String email, String celular) {
    if(nome.isEmpty() || cpf.isEmpty() || dataNascimento.isEmpty() || email.isEmpty() || celular.isEmpty()) {
      return true;
    }

    return false;
  }

  public static boolean clienteExistente(String cpf) {
    if(Database.getCliente(cpf) != null) {
      return true;
    }

    return false;
  }

  public static boolean nomeInvalido(String nome) {
    return !nome.matches("[a-zA-ZÀ-ÿ\\s'-]+");
  }

  public static boolean cpfInvalido(String cpf) {
    if(cpf.matches("\\d{11}")) {
      int soma = 0;
      for(int i = 1; i <= 9; i++) {
        soma += i * Integer.parseInt(cpf.substring(i-1, i));
      }
      int digito1 = soma % 11 == 10 ? 0 : soma % 11;

      soma = 0;
      for(int i = 1; i <= 9; i++) {
        soma += i * Integer.parseInt(cpf.substring(i, i+1));
      }
      int digito2 = soma % 11 == 10 ? 0 : soma % 11;

      String digitos = Integer.toString(digito1) + Integer.toString(digito2);
      String verificadores = cpf.substring(9);

      return (digitos.equals(verificadores)) ? false : true;
    }

    return true;
  }

  public static boolean dataNascimentoInvalida(String dataNascimento) {
    if(dataNascimento.matches("\\d{4}-\\d{2}-\\d{2}")) {
      try {
        LocalDate.parse(dataNascimento);
        return false;
      }
      catch(DateTimeParseException e) {
        return true;
      }
    }

    return true;
  }

  public static boolean emailInvalido(String email) {
    return !email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
  }

  public static boolean celularInvalido(String celular) {
    return !celular.matches("\\d{2} \\d{4,5}-\\d{4}");
  }

  public static boolean menorDeIdade(String dataNascimento) {
    int anoAtual = Year.now().getValue();
    int anoCliente = Integer.parseInt(dataNascimento.substring(0, 4));

    return (anoAtual - anoCliente >= 18) ? false : true;
  }
}
