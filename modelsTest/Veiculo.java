public class Veiculo {

  // Atributos
  private String placa;
  private String marca;
  private String modelo;
  private String cor;
  private int anoFabricacao;
  private Grupo grupo;
  private Status status;

  // Construtor
  public Veiculo(String placa, String marca, String modelo, String cor,
                 int anoFabricacao, Grupo grupo) {
    this.placa = placa;
    this.marca = marca;
    this.modelo = modelo;
    this.cor = cor;
    this.anoFabricacao = anoFabricacao;
    this.grupo = grupo;
    this.status = Status.DISPONIVEL;
 }

  // toString()
  public String toString() {
    return "===================\n" +
           "Informacoes Veiculo\n" +
           " -> Placa: " + this.placa +
           "\n -> Marca: " + this.marca +
           "\n -> Modelo: " + this.modelo +
           "\n -> Cor: " + this.cor +
           "\n -> Ano: " + this.anoFabricacao +
           "\n -> Grupo: " + this.grupo +
           "\n -> Status: " + this.status +
           "\n===================\n";
  }
}
