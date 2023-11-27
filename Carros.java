public class Carros {
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private int ano;
    private String grupo;
    private String status;
        

    // construtor
    public Carros(String placa, String marca, String modelo, String cor, int ano, String grupo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.grupo = grupo;
        this.status = "disponível";   // Por padrão, todo veículo adicionado tem o status disponível


        // Escreve no banco de dados de veiculos
        escreverNoArquivo();
    }

    // Getters para os campos privados
    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public int getAno() {
        return ano;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getStatus() {
        return status;
    }

    private void escreverNoArquivo() {
        salvaNoBD.escreverNoArquivo(this); // Chama o método estático da classe salvaNoBD
    }

    public String toString() {
        return String.format("%-10s%-10s%-15s%-15s%-10d%-10s%-15s%n", this.placa, this.marca, this.modelo, this.cor, this.ano, this.grupo, this.status);
    }
}
