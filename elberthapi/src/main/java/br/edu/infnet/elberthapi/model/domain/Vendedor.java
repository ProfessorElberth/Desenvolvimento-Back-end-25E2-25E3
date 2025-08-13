package br.edu.infnet.elberthapi.model.domain;

import br.edu.infnet.elberthapi.model.exceptions.BonusInvalidoException;

public class Vendedor {

	public String nome;
	public int matricula;
	public double salario;
	public boolean ativo;
	
	public Vendedor(String nome, int matricula, double salario, boolean ativo) {
		this.nome = nome;
		this.matricula = matricula;
		this.salario = salario;
		this.ativo = ativo;
	}
	
	@Override
	public String toString() {

		return "[Vendedor: nome = " + nome +"]";
	}
	
	public double calcularSalario(double bonus) {
		
		if(bonus <= 0) {
			throw new BonusInvalidoException("O valor do bônus não pode ser negativo ou igual a zero!");
		}

		return ativo ? salario + bonus : 0;
	}
}