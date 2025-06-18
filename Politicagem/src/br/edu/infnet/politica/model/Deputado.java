package br.edu.infnet.politica.model;

import br.edu.infnet.politica.model.superclass.Politico;

public class Deputado extends Politico {

	public Deputado(String nome, String partido) {
		super(nome, partido);
	}
	
	@Override
	public String toString() {

		return "Deputado: " + this.getNome();
	}

	@Override
	public void fazerDiscurso() {
		System.out.println("(Deputado) fala sobre orçamento federal!");
	}

	@Override
	public double calcularSalario() {

		return 16000;
	}
}