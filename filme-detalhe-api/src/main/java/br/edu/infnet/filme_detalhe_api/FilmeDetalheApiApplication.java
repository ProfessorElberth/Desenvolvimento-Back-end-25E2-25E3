package br.edu.infnet.filme_detalhe_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FilmeDetalheApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmeDetalheApiApplication.class, args);
	}

}
