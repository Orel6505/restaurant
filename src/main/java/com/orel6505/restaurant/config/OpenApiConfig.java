package com.orel6505.restaurant.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Restaurant Management API",
        version = "v1.0.0",
        description = "A comprehensive REST API for managing restaurant operations including dishes, ingredients, orders, and user roles.",
        contact = @Contact(
            name = "Restaurant API",
            url = "http://localhost:8080"
        )
    )
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER,
    description = "Provide the JWT token in the Authorization header: **'Bearer <token>'**"
)
public class OpenApiConfig {
    // OpenAPI configuration bean
}
