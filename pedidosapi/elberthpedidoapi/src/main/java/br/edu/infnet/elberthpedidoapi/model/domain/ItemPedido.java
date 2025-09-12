package br.edu.infnet.elberthpedidoapi.model.domain;

import java.math.BigDecimal;

public class ItemPedido {

	private int quantidade;
	private Produto produto;
	
	public BigDecimal calcularSubTotal(){
		
		if(quantidade <= 0) {
			return BigDecimal.ZERO;
		}
		
		if(produto == null) {
			return BigDecimal.ZERO;
		}
		
		BigDecimal valor = produto.getValor();
		if(valor == null) {
			return BigDecimal.ZERO;
		}

		return valor.multiply(new BigDecimal(quantidade));
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
