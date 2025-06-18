package br.edu.infnet.politica.model;

import br.edu.infnet.politica.interfaces.Comissionado;

public class Consultor implements Comissionado {

	private String nome;
	private double valorBase;
	private double gratificacao;
	private double desconto;
	

	public Consultor() {
		this.valorBase = 5000;
		this.gratificacao = 2500;
		this.desconto = 1000;
	}

	@Override
	public double calcularSalario() {

		return this.valorBase + this.gratificacao - this.desconto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorBase() {
		return valorBase;
	}

	public void setValorBase(double valorBase) {
		this.valorBase = valorBase;
	}

	public double getGratificacao() {
		return gratificacao;
	}

	public void setGratificacao(double gratificacao) {
		this.gratificacao = gratificacao;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
}