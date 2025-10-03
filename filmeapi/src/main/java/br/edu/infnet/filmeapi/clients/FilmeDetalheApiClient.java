package br.edu.infnet.filmeapi.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.filmeapi.model.domain.Ator;
import br.edu.infnet.filmeapi.model.domain.Localizacao;

@FeignClient(name = "filmeDetalheApiClient", url = "${url.filme.detalhe}")
public interface FilmeDetalheApiClient {

	@GetMapping("/endereco/{cep}")
	Localizacao obterPorCep(@PathVariable("cep") String cep);
	
	@GetMapping("/pessoa/{id}")
	Ator obterPorId(@PathVariable Integer id);
}