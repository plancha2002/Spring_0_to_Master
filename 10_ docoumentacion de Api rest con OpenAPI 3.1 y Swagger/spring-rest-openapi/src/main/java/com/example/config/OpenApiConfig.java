package com.example.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
http://localhost:8080/v3/api-docs -> JSON

http://localhost:8080/swagger-ui/index.html -> GUI
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openApi(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Employees API")
                                .description("API REST para empleados de la empresa")
                                .license(new License().name("Apache 2.0").url("https://www.github.com/"))

                )
                .externalDocs(new ExternalDocumentation()
                        .description("Wiki docs")
                        .url("https://www.github.com/"));
    }
}
