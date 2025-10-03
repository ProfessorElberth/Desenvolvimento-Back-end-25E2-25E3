package br.edu.infnet.filmeapi.model.service;

import org.springframework.stereotype.Service;

import br.edu.infnet.filmeapi.clients.FilmeDetalheApiClient;
import br.edu.infnet.filmeapi.model.domain.Ator;

@Service
public class AtorService {

	private final FilmeDetalheApiClient filmeDetalheApiClient;
	
	public AtorService(FilmeDetalheApiClient filmeDetalheApiClient) {
		this.filmeDetalheApiClient = filmeDetalheApiClient;
	}

	public Ator obterPorId(Integer id) {
		
		Ator ator = filmeDetalheApiClient.obterPorId(id);
		
		return ator;
	}
}