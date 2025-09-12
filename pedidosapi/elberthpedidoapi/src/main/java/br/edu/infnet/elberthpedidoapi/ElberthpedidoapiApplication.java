package br.edu.infnet.elberthpedidoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ElberthpedidoapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElberthpedidoapiApplication.class, args);
	}

}
