package com.litora.bookreview.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI litoraOpenAPI() {
        final String securitySchemeName = "basicAuth";

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
                                .url("https://springdoc.org")))
                // Apply the security scheme globally across all endpoints (adds lock icon & Authorize button)
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // Define reusable OpenAPI components
                .components(new Components()
                        // Register a security scheme for HTTP Basic Authentication
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName) // Name identifier for the scheme
                                        .type(SecurityScheme.Type.HTTP) // Authentication type: HTTP protocol
                                        .scheme("basic"))); // Specific scheme: HTTP Basic (username/password)
    }
}
