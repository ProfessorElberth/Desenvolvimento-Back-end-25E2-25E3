package br.edu.infnet.model;

public class Vendedor {

	private String cpf;
	private String nome;
	private String email;
	private double salario;
	
	private double totalVenda;

	public void registrarVenda(double venda) {
		totalVenda = totalVenda + venda;
	}
	
	public double getTotalVenda() {
		return this.totalVenda;
	}
	
	@Override
	public String toString() {
		return String.format("Valor total de venda: %.2f", this.totalVenda);
	}
	
	public void imprimir() {
		System.out.println("Relatório Gerencial");
		System.out.println(toString());		
		System.out.println("Instituto Infnet");
	}
	
	//TODO Implementar o cálculo de comissão
	/*
	public calcularComissao() {
		
	}
	*/
}