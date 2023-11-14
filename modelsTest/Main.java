public class Main {

	public static void main(String[] args) {
		Parametros[] paramOperacionais = new Parametros[3];
		paramOperacionais[0] = new Parametros(Grupo.BASICO, 100, 200, 30, 50, 15);
		paramOperacionais[1] = new Parametros(Grupo.PADRAO, 150, 200, 35, 60, 25);
		paramOperacionais[2] = new Parametros(Grupo.PREMIUM, 250, 220, 70, 100, 60);

		Veiculo veic1 = new Veiculo("ABC1D23", "Fiat", "Mobi", "branco", 2022, Grupo.BASICO);
		Cliente clie1 = new Cliente("43988228010", "Antônio José da Silva", "2002-01-14", "a.j.silva@terra.com.br", "87 9918-5739");

		System.out.println("=============================");
		System.out.println("Parametros Operacionais:");
		for(int i = 0; i < paramOperacionais.length; i++) {
		  System.out.println(paramOperacionais[i]);
		}
		System.out.println("=============================\n");

		System.out.println(veic1);
		System.out.println(clie1);
	}
}
