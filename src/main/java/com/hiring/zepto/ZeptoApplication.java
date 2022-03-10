package com.hiring.zepto;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@EnableAutoConfiguration
@SpringBootApplication
public class ZeptoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZeptoApplication.class, args);
		log.info("IRCTC Backend application started");
	}
	@Bean
	public OpenAPI zeptoOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("ZEPTO- IRCTC BACKEND API")
						.description("Zepto Hiring challenge")
						.version("v0.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));

	}
}
