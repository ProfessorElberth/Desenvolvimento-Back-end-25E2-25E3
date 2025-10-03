package br.edu.infnet.filmeapi.model.service;

import org.springframework.stereotype.Service;

import br.edu.infnet.filmeapi.clients.FilmeDetalheApiClient;
import br.edu.infnet.filmeapi.model.domain.Localizacao;

@Service
public class LocalizacaoService {

	private final FilmeDetalheApiClient filmeDetalheApiClient;
	
	public LocalizacaoService(FilmeDetalheApiClient filmeDetalheApiClient) {
		this.filmeDetalheApiClient = filmeDetalheApiClient;
	}

	public Localizacao obterPorCep(String cep) {
		return filmeDetalheApiClient.obterPorCep(cep);
	}
}
