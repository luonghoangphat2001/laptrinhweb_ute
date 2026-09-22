package com.nexus.portal.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "BearerAuth";

    @Value("${app.api.version}")
    private String apiVersion;

    @Value("${app.api.base-url}")
    private String apiBaseUrl;

    @Value("${app.api.prefix}")
    private String apiPrefix;

    @Bean
    public OpenAPI customOpenAPI() {
        Server primaryServer = new Server()
                .url(apiBaseUrl)
                .description("Environment Configured Base URL");

        Server relativeServer = new Server()
                .url(apiPrefix)
                .description("Relative Context API Server");

        return new OpenAPI()
                .info(new Info()
                        .title("Nexus Portal REST API")
                        .version(apiVersion)
                        .description("RESTful API documentation for Nexus Portal backend services including Authentication, User Management, and Dashboard Analytics.")
                        .contact(new Contact()
                                .name("Nexus Portal Team")
                                .email("support@nexusportal.com")
                                .url(apiBaseUrl))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(primaryServer, relativeServer))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter your JWT Bearer token to authorize requests (e.g. eyJhbGciOi...)")));
    }
}

