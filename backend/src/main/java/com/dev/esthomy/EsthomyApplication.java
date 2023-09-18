package com.dev.esthomy;

import com.dev.esthomy.service.FileUploader;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.io.File;

@EnableWebSocketMessageBroker
@SpringBootApplication
public class EsthomyApplication {
	public static void main(String[] args) {
		SpringApplication.run(EsthomyApplication.class, args);
	}

}
