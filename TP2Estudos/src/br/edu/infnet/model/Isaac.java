package br.edu.infnet.model;

public class Isaac {
	
	private String nome;
	
	private static float salario = 100;
	
	public Isaac(String nome) {
		this.nome = nome;
	}

	public void falou(float adicional) {
		System.out.println(nome + " falou sobre m�todos est�ticos.");
		salario = salario + adicional;
	}
	
	public static void disse() {
		System.out.println("Ele disse que esse neg�cio � muito dif�cil! ["+salario+"]");
	}
}
