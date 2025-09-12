package br.edu.infnet.elberthpedidoapi.model.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.elberthpedidoapi.model.domain.Endereco;

@FeignClient(name = "viacep", url = "${api.viacep.url}")
public interface ViaCepFeignClient {

	@GetMapping("/{cep}/json/")
	Endereco findByCep(@PathVariable String cep);
}