package br.edu.infnet.politica.model.superclass;

import br.edu.infnet.politica.interfaces.Comissionado;

public abstract class Politico implements Comissionado {

	private String nome;
	private String partido;

	public Politico() {
		
	}

	public Politico(String nome, String partido) {
		this();
	
		this.nome = nome;
		this.partido = partido;
	}
	
	public abstract void fazerDiscurso();
		
	public final void imprimir() {
		System.out.println("Sou político " + this.nome + " do partido " + this.partido);
	}

	@Override
	public String toString() {

		return String.format("Nome = %s; Partido = %s", this.nome, this.partido);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPartido() {
		return partido;
	}
	public void setPartido(String partido) {
		this.partido = partido;
	}
	
	
}