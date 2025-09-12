package br.edu.infnet.elberthpedidoapi.model.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {

	private String numero;
	private LocalDateTime dataCriacao;
	private String cliente;
	private StatusPedido status;
	private List<ItemPedido> itens;
	
	public Pedido() {
		this.setDataCriacao(LocalDateTime.now());
		this.setStatus(StatusPedido.PENDENDE);
		this.setNumero(UUID.randomUUID().toString());
		this.setCliente(null);
		this.setItens(new ArrayList<ItemPedido>());
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	public List<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
}
