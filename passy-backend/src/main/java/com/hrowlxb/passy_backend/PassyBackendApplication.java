package com.hrowlxb.passy_backend;

import com.hrowlxb.passy_backend.global.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class PassyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassyBackendApplication.class, args);
	}

}
