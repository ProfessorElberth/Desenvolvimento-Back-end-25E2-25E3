package br.edu.infnet.model;

import br.edu.infnet.exceptions.CpfInvalidoException;
import br.edu.infnet.exceptions.ValorNegativoException;

public class Vendedor {

	private String cpf;
	private String nome;
	private String email;
	private double salario;

	private double totalVenda;

	public Vendedor() {
		
	}
	
	public Vendedor(String cpf, String nome, String email, double salario) {
		
		if(cpf == null || cpf.trim().isEmpty() || cpf.length() != 11) {
			throw new CpfInvalidoException("CPF inválido");
		}
		
		if(salario < 0) {
			throw new ValorNegativoException("O salário " + salario +" é inválido");
		}
		
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.salario = salario;
	}
	
	public void registrarVenda(double venda) {
		totalVenda = totalVenda + venda;
	}
	
	public double getTotalVenda() {
		return this.totalVenda;
	}
	
	@Override
	public String toString() {
		
		return String.format("%s - %s - %s - %.2f", this.cpf, this.nome, this.email, this.salario);
	}
	
	public void imprimir() {
		System.out.println("Relatório Gerencial");
		System.out.println(toString());		
		System.out.println(String.format("Valor total de venda: %.2f", this.totalVenda));
		System.out.println("Instituto Infnet");
	}
	
	//TODO Implementar o cálculo de comissão
	/*
	public calcularComissao() {
		
	}
	*/
}