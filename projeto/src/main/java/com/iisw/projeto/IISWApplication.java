package com.iisw.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication //(exclude = {SecurityAutoConfiguration.class })
@OpenAPIDefinition(info = @Info(title = "IISW", version = "1", description = "Back-end IISW"))
//@Import(OpenAPIConfiguration.class) // importando a classe de configuração do OpenAPI

//@EnableOpenApi
@EnableScheduling

public class IISWApplication {

	public static void main(String[] args) {
		SpringApplication.run(IISWApplication.class, args);
	}
 
}
