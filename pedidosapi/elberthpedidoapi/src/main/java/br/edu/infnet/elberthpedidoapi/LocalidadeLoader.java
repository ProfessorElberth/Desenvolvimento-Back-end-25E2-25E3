package br.edu.infnet.elberthpedidoapi;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.elberthpedidoapi.model.domain.LocalidadeQueryResult;
import br.edu.infnet.elberthpedidoapi.model.service.LocalidadeService;

@Component
public class LocalidadeLoader implements ApplicationRunner {

	private final LocalidadeService localidadeService;
	
	public LocalidadeLoader(LocalidadeService localidadeService) {
		this.localidadeService = localidadeService;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		
		LocalidadeQueryResult localidadeQueryResult = localidadeService.obterLocalidadesPorCep("20010020");
		
		System.out.println("[RESULTADO] MEU CEP: " + localidadeQueryResult.getCepConsultado());
		System.out.println("[RESULTADO] MEU MUNICÍPIO: " + localidadeQueryResult.getMunicipioOrigem());
		System.out.println("[RESULTADO] MEU UF: " + localidadeQueryResult.getUfOrigem());
		System.out.println("[RESULTADO] OUTROS MUNICÍPIOS: " + localidadeQueryResult.getOutrosMunicipiosNaUF());
	}

}