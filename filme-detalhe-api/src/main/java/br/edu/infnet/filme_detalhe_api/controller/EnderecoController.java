package br.edu.infnet.filme_detalhe_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.filme_detalhe_api.model.clients.ViaCepFeignClient;
import br.edu.infnet.filme_detalhe_api.model.domain.Endereco;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

	private final ViaCepFeignClient viaCepFeignClient;
	
	public EnderecoController(ViaCepFeignClient viaCepFeignClient) {
		this.viaCepFeignClient = viaCepFeignClient;
	}
	
	@GetMapping("/{cep}")
	public Endereco obterPorCep(@PathVariable("cep") String cep) {

		Endereco endereco = viaCepFeignClient.obterPorCep(cep);

		return endereco;
	}
}