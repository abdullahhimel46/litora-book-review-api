package com.litora.bookreview.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI litoraOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Litora Book Review API")
                        .description("RESTful API documentation for managing books, reviews, and ratings in the Litora platform.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Abdullah Himel")
                                .email("abdullahhimel46@gmail.com")
                                .url("https://github.com/abdullahhimel46"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")));
    }
}
