package br.edu.infnet.testes;

public class Vendedor {

	private String cpf;
	private String nome;
	private String email;
	private double salario;

	private double totalVenda;

	public Vendedor() {
		
	}
	
	public Vendedor(String cpf, String nome, String email, double salario) {
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
}