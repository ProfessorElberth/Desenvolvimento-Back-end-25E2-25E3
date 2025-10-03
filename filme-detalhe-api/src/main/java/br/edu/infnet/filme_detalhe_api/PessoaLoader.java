package br.edu.infnet.filme_detalhe_api;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.filme_detalhe_api.model.domain.Pessoa;
import br.edu.infnet.filme_detalhe_api.model.service.PessoaService;

@Component
public class PessoaLoader implements ApplicationRunner {

	private final PessoaService pessoaService;
	
	public PessoaLoader(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Integer id = 1;
		
		Pessoa pessoa = pessoaService.obterPessoaPorId(id);
		
		System.out.println("Pessoa - Nome: " + pessoa.getName());
		System.out.println("Pessoa - URL: " + pessoa.getUrl());
	}
}