package com.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsDemoApplication.class, args);
	}

}
