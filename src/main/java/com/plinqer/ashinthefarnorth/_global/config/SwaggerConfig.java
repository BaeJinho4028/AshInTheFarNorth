package com.plinqer.ashinthefarnorth._global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
            .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
            .title("AshInTheFarNorth API")
            .description("최북단 사는 최재애쉬 API 문서입니다.")
            .version("v0.0.1");
    }
}
