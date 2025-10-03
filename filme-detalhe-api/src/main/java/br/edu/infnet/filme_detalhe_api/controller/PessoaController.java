package br.edu.infnet.filme_detalhe_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.filme_detalhe_api.model.domain.Pessoa;
import br.edu.infnet.filme_detalhe_api.model.service.PessoaService;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

	private final PessoaService pessoaService;
	
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@GetMapping("/{id}")
	public Pessoa obterPessoaPorId(@PathVariable Integer id) {
		
		Pessoa pessoa = pessoaService.obterPessoaPorId(id);
		
		return pessoa;
	}
}