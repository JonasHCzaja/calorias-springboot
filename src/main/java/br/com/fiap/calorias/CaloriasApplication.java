package br.com.fiap.calorias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "br.com.fiap.calorias.model")
@EnableJpaRepositories(basePackages = "br.com.fiap.calorias.repository")
public class CaloriasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaloriasApplication.class, args);
	}

}
