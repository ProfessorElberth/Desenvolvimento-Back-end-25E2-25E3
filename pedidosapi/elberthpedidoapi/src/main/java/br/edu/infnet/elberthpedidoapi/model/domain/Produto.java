package br.edu.infnet.elberthpedidoapi.model.domain;

import java.math.BigDecimal;

public class Produto {

	private String codigo;
	private String nome;
	private TipoProduto tipo;
	private BigDecimal valor;

	public Produto(String codigo, String nome, TipoProduto tipo, BigDecimal valor) {
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setTipo(tipo);
		this.setValor(valor);
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TipoProduto getTipo() {
		return tipo;
	}
	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}