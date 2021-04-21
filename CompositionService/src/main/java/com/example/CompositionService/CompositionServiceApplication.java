package com.example.CompositionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CompositionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompositionServiceApplication.class, args);
	}

}
