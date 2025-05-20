package br.edu.infnet.model;

public class Isaac {
	
	private String nome;
	
	private static float salario = 100;
	
	public Isaac(String nome) {
		this.nome = nome;
	}

	public void falou(float adicional) {
		System.out.println(nome + " falou sobre métodos estáticos.");
		salario = salario + adicional;
	}
	
	public static void disse() {
		System.out.println("Ele disse que esse negócio é muito difícil! ["+salario+"]");
	}
}
