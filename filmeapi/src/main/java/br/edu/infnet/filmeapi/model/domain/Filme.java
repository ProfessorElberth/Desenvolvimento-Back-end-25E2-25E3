package br.edu.infnet.filmeapi.model.domain;

import java.time.LocalDateTime;

import br.edu.infnet.filmeapi.auxiliares.Constante;

public class Filme {

	private Integer id;
	
	private String titulo;
	private int anoLancamento;
	private double avaliacaoMedia;
	private boolean emCartaz;
	private Genero genero;
	private Ator atorPrincipal;

	public Filme() {
		this.setAnoLancamento(LocalDateTime.now().getYear());
		this.setAvaliacaoMedia(Constante.MENOR_MEDIA_AVALIACAO);
		this.setGenero(Genero.OUTROS);
	}
	
	public Filme(String titulo) {
		this();
		this.setTitulo(titulo);
	}

	@Override
	public String toString() {
		return String.format("[Filme] id = %d, titulo = %s, anoLancamento = %d, avaliacaoMedia = %.2f, emCartaz = %s, genero = %s, ator = %s", 
				this.getId(),
				this.getTitulo(), 
				this.getAnoLancamento(),
				this.getAvaliacaoMedia(),
				this.isEmCartaz(),
				this.getGenero(),
				this.getAtorPrincipal().getName()
			);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public double getAvaliacaoMedia() {
		return avaliacaoMedia;
	}

	public void setAvaliacaoMedia(double avaliacaoMedia) {
		this.avaliacaoMedia = avaliacaoMedia;
	}

	public boolean isEmCartaz() {
		return emCartaz;
	}

	public void setEmCartaz(boolean emCartaz) {
		this.emCartaz = emCartaz;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Ator getAtorPrincipal() {
		return atorPrincipal;
	}

	public void setAtorPrincipal(Ator atorPrincipal) {
		this.atorPrincipal = atorPrincipal;
	}
}