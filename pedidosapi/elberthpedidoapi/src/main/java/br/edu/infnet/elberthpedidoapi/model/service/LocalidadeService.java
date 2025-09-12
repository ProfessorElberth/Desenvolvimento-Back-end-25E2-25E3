package br.edu.infnet.elberthpedidoapi.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberthpedidoapi.model.clients.IbgeLocalidadesFeignClient;
import br.edu.infnet.elberthpedidoapi.model.clients.ViaCepFeignClient;
import br.edu.infnet.elberthpedidoapi.model.domain.Endereco;
import br.edu.infnet.elberthpedidoapi.model.domain.IbgeMunicipio;
import br.edu.infnet.elberthpedidoapi.model.domain.IbgeUF;
import br.edu.infnet.elberthpedidoapi.model.domain.LocalidadeQueryResult;

@Service
public class LocalidadeService {

	private final ViaCepFeignClient viaCepFeignClient;
	private final IbgeLocalidadesFeignClient ibgeLocalidadesFeignClient;
	
	public LocalidadeService(ViaCepFeignClient viaCepFeignClient, IbgeLocalidadesFeignClient ibgeLocalidadesFeignClient) {
		this.viaCepFeignClient = viaCepFeignClient;
		this.ibgeLocalidadesFeignClient = ibgeLocalidadesFeignClient;
	}
	
	public LocalidadeQueryResult obterLocalidadesPorCep(String cep) {

		Endereco endereco = viaCepFeignClient.findByCep(cep);
		String municipioOrigem = endereco.getLocalidade();
		String ufOrigem = endereco.getUf();
		
		
		List<IbgeUF> estados = ibgeLocalidadesFeignClient.obterEstados();
		
		List<IbgeMunicipio> municipios = ibgeLocalidadesFeignClient.obterMunicipiosPorUf(estados.get(0).getId());

		LocalidadeQueryResult localidadeQueryResult = new LocalidadeQueryResult();
		localidadeQueryResult.setCepConsultado(cep);
		localidadeQueryResult.setMunicipioOrigem(municipioOrigem);
		localidadeQueryResult.setUfOrigem(ufOrigem);
		localidadeQueryResult.setOutrosMunicipiosNaUF(municipios.stream().map(IbgeMunicipio::getNome).toList());
		
		return localidadeQueryResult;
	}
}
