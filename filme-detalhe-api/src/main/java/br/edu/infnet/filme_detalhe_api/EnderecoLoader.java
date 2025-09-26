package br.edu.infnet.filme_detalhe_api;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.filme_detalhe_api.model.clients.ViaCepFeignClient;
import br.edu.infnet.filme_detalhe_api.model.domain.Endereco;

@Component
public class EnderecoLoader implements ApplicationRunner {

	private final ViaCepFeignClient viaCepFeignClient;
	
	public EnderecoLoader(ViaCepFeignClient viaCepFeignClient) {
		this.viaCepFeignClient = viaCepFeignClient;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		String cep = "20010020";

		Endereco endereco = viaCepFeignClient.obterPorCep(cep);
		
		System.out.println("Cep: " + endereco.getCep());
		System.out.println("Bairro: " + endereco.getBairro());
		System.out.println("UF: " + endereco.getUf());
	}
}