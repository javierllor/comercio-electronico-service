package com.gft.commercial.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Prices service API",
                version = "api-paths",
                description = "GFT - Prices service API")
)
public class OpenApiConfig {

}
