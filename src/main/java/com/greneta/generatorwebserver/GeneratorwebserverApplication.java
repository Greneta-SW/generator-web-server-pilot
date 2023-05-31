package com.greneta.generatorwebserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GeneratorwebserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneratorwebserverApplication.class, args);
	}

}
