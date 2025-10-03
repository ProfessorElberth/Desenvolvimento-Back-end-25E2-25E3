package br.edu.infnet.filme_detalhe_api.model.service;

import org.springframework.stereotype.Service;

import br.edu.infnet.filme_detalhe_api.model.clients.ViaCepFeignClient;
import br.edu.infnet.filme_detalhe_api.model.domain.Endereco;

@Service
public class EnderecoService {

	private final ViaCepFeignClient viaCepFeignClient;
	
	public EnderecoService(ViaCepFeignClient viaCepFeignClient) {
		this.viaCepFeignClient = viaCepFeignClient;
	}
	
	public Endereco obterPorCep(String cep) {
		return viaCepFeignClient.obterPorCep(cep);
	}

}
