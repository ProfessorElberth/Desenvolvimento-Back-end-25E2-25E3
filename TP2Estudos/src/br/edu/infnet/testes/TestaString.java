package br.edu.infnet.testes;

public class TestaString {

	public static void main(String[] args) {
		
		float[] salarios = new float[3];
		salarios[0] = 1000;
		salarios[1] = 2000;
		salarios[2] = 3000;
		
		int[] idades = {10, 20, 30};
		
		String nome = " Elberth Lins Moraes ";
		
		String[] nomes = nome.trim().split(" ");
		
		System.out.println("qtde nomes: " + nomes.length);//
		System.out.println("qtde salarios: " + salarios.length);//
		System.out.println("qtde idades: " + idades.length);//

		//foreach
		
		for(float salario : salarios) {
			System.out.println(salario);
		}
		
		for(String n : nomes) {
			System.out.println(n);
		}
		
		for(int idade : idades) {
			System.out.println(idade);
		}
		/*
		for(int i = 0; i < 3; i++) {
			System.out.printf("%s - %.2f - %d\n", 
						nomes[i],
						salarios[i],
						idades[i]
					);
		}
		*/
		
		//trim:
		
		//length:
	}
}
