package br.edu.infnet.tarefaapi.model.domain;

import java.time.LocalDateTime;

public class Tarefa {

	private Long id;

	private String titulo;
	private String descricao;
	private StatusTarefa status;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataConclusao;

	public Tarefa(Long id, String titulo, String descricao) {

		if(id == null) {
			throw new IllegalArgumentException("ID da tarefa não pode ser nulo!");
		}

		if(titulo == null) {
			throw new IllegalArgumentException("O título da tarefa é obrigatório.");
		}
		
		if(titulo.trim().isEmpty()) {
			throw new IllegalArgumentException("O novo título não pode ser vazio.");
		}

		this.setId(id);
		this.setTitulo(titulo);
		this.setDescricao(descricao);
		this.setStatus(StatusTarefa.PENDENTE); // Status inicial da especificação
		this.setDataCriacao(LocalDateTime.now());
		this.setDataConclusao(null); // Uma nova tarefa não tem data de conclusão
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusTarefa getStatus() {
		return status;
	}

	public void setStatus(StatusTarefa status) {
		this.status = status;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDateTime dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
}