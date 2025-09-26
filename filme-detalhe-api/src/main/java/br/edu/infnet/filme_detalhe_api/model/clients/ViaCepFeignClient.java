package br.edu.infnet.filme_detalhe_api.model.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.filme_detalhe_api.model.domain.Endereco;

@FeignClient(name = "viaCepFeignClient", url = "${viacep.url}")
public interface ViaCepFeignClient {

	@GetMapping("/{codigoPostal}/json/")
	Endereco obterPorCep(@PathVariable("codigoPostal") String cep);
}