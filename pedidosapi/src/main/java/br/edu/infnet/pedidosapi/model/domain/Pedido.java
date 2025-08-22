package br.edu.infnet.pedidosapi.model.domain;

import java.util.List;

public class Pedido {

	private Long id;
	private List<ItemPedido> itens;
	
	public Pedido(Long id, List<ItemPedido> itens) {
		this.setId(id);
		this.setItens(itens);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
}