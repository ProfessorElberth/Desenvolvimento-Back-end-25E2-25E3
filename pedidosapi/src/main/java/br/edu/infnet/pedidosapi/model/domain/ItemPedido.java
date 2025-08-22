package br.edu.infnet.pedidosapi.model.domain;

public class ItemPedido {
	private Produto produto;
	private int quantidade;
	
	public ItemPedido(Produto produto, int quantidade) {
		this.setProduto(produto);
		this.setQuantidade(quantidade);
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}