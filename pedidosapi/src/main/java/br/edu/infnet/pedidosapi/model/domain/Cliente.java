package br.edu.infnet.pedidosapi.model.domain;

public class Cliente {
	
	private Long id;
	private String nome;

	public Cliente(Long id, String nome) {
		this.setId(id);
		this.setNome(nome);
		
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		return "[Cliente] id: " + this.getId() + "; nome: " + this.getNome();
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
}