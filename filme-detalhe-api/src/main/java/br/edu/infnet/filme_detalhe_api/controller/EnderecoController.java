package br.edu.infnet.filme_detalhe_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.filme_detalhe_api.model.domain.Endereco;
import br.edu.infnet.filme_detalhe_api.model.service.EnderecoService;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

	private final EnderecoService enderecoService;
	
	public EnderecoController(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}
	
	@GetMapping("/{cep}")
	public Endereco obterPorCep(@PathVariable("cep") String cep) {

		Endereco endereco = enderecoService.obterPorCep(cep);

		return endereco;
	}
}