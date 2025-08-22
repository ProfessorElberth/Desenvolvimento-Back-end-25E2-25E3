package br.edu.infnet.pedidosapi.model.domain;

import java.math.BigDecimal;

public class Produto {
	private Long id;
	private String nome;
	private BigDecimal preco;
	
	public Produto(Long id, String nome, BigDecimal preco) {
		this.setId(id);
		this.setNome(nome);
		this.setPreco(preco);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}