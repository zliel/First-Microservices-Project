package com.example.MusicReviewService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MusicReviewServiceApplication {

	@Bean
	public WebClient getWebClient() {
		return WebClient.create();
	}

	public static void main(String[] args) {
		SpringApplication.run(MusicReviewServiceApplication.class, args);
	}

}
