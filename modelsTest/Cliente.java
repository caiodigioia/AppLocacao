public class Cliente {

  // Atributos
  private String cpf;
  private String nome;
  private String dataNascimento;
  private String email;
  private String numCelular;

  // Construtor
  public Cliente(String cpf, String nome, String dataNascimento, String email,
                 String numCelular) {
    this.cpf = cpf;
    this.nome = nome;
    this.dataNascimento = dataNascimento;
    this.email = email;
    this.numCelular = numCelular;
 }

  // toString()
  public String toString() {
    return "===================\n" +
           "Informacoes Cliente\n" +
           " -> CPF: " + this.cpf +
           "\n -> Nome: " + this.nome +
           "\n -> Nascimento: " + this.dataNascimento +
           "\n -> Email: " + this.email +
           "\n -> Celular: " + this.numCelular +
           "\n===================\n";
  }
}
