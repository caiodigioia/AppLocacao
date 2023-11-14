public class Parametros {

  // Atributos
  private Grupo grupo;
  private double valorDiaria;
  private double valorTanque;
  private double valorLimpezaExt;
  private double valorLimpezaInt;
  private double valorDiariaSeguro;

  // Construtor
  public Parametros(Grupo grupo, double diaria, double tanque, double limpezaExt,
                    double limpezaInt, double seguro) {
    this.grupo = grupo;
    this.valorDiaria = diaria;
    this.valorTanque = tanque;
    this.valorLimpezaExt = limpezaExt;
    this.valorLimpezaInt = limpezaInt;
    this.valorDiariaSeguro = seguro;
  }

  // toString()
  public String toString() {
    return grupo + " R$" + this.valorDiaria + " R$" +
           this.valorTanque + " R$" + this.valorLimpezaExt + " R$" +
           this.valorLimpezaInt + " R$" + valorDiariaSeguro;
  }
}
