public class Cliente {

  // Atributos
  private String cpf;
  private String nome;
  private String dataNascimento;
  private String email;
  private String celular;

  // Construtor
  public Cliente(String cpf, String nome, String dataNascimento, String email, String celular) {
    setCpf(cpf);
    setNome(nome);
    setDataNascimento(dataNascimento);
    setEmail(email);
    setCelular(celular);
  }

  // Getters e Setters
  public String getCpf() {
    return this.cpf;
  }
  private void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getNome() {
    return this.nome;
  }
  private void setNome(String nome) {
    this.nome = nome;
  }

  public String getDataNascimento() {
    return this.dataNascimento;
  }
  private void setDataNascimento(String dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public String getEmail() {
    return this.email;
  }
  private void setEmail(String email) {
    this.email = email;
  }

  public String getCelular() {
    return this.celular;
  }
  private void setCelular(String celular) {
    this.celular = celular;
  }

  // Métodos
  public String toString() {
    return String.format("%s\t%s\t%s\t%s\t%s", getCpf(), getNome(), getDataNascimento(), getEmail(), getCelular());
  }
}
