package br.edu.infnet.filme_detalhe_api.model.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.filme_detalhe_api.model.domain.Pessoa;

@FeignClient(name = "tvMazeFeignClient", url = "${tvmaze.url}")
public interface TvMazeFeignClient {

	@GetMapping("/people/{id}")
	public Pessoa obterPessoaPorId(@PathVariable Integer id);
}