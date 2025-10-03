package br.edu.infnet.filme_detalhe_api.model.service;

import org.springframework.stereotype.Service;

import br.edu.infnet.filme_detalhe_api.model.clients.TvMazeFeignClient;
import br.edu.infnet.filme_detalhe_api.model.domain.Pessoa;

@Service
public class PessoaService {

	private final TvMazeFeignClient tvMazeFeignClient;
	
	public PessoaService(TvMazeFeignClient tvMazeFeignClient) {
		this.tvMazeFeignClient = tvMazeFeignClient;
	}
	
	public Pessoa obterPessoaPorId(Integer id) {
		return tvMazeFeignClient.obterPessoaPorId(id);
	}
}
