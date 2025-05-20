package br.edu.infnet.testes;

public class Venda {

	String descricao;
	int quantidade;
	float preco;
	boolean desconto;
	int categoria;
	float valorVenda;	

	public Venda(String descricao, float preco, int categoria) {
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}
}
