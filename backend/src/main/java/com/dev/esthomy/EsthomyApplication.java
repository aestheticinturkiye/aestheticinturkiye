package com.dev.esthomy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@EnableWebSocketMessageBroker
@SpringBootApplication
public class EsthomyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsthomyApplication.class, args);
	}

}
