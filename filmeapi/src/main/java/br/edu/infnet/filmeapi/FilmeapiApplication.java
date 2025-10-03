package br.edu.infnet.filmeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FilmeapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmeapiApplication.class, args);
	}

}
