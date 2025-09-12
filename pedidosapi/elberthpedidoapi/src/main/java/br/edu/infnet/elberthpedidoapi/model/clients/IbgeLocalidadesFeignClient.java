package br.edu.infnet.elberthpedidoapi.model.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.elberthpedidoapi.model.domain.IbgeMunicipio;
import br.edu.infnet.elberthpedidoapi.model.domain.IbgeUF;

@FeignClient(name = "ibge-localidades", url = "${api.ibge.url}")
public interface IbgeLocalidadesFeignClient {

	@GetMapping("/estados")
	List<IbgeUF> obterEstados();
	
	@GetMapping("estados/{ufId}/municipios")
	List<IbgeMunicipio> obterMunicipiosPorUf(@PathVariable Long ufId);
}