package br.edu.infnet.model;

public class Aluno {

	private String nome;
	private int matricula;
	
	public Aluno(String nome, int matricula) {
		this.nome = nome;
		this.matricula = matricula;
	}
	
	@Override
	public String toString() {
		return "O nome do aluno é "+nome+" com a matrícula " + matricula;
	}
}