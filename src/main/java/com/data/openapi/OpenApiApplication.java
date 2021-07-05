package com.data.openapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class OpenApiApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(OpenApiApplication.class, args);
	}

}
