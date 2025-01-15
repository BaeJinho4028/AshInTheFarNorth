package com.plinqer.ashinthefarnorth._global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    private final String serverUrl;

    public SwaggerConfig(
        @Value("${springdoc.server.url}") String serverUrl
    ) {
        this.serverUrl = serverUrl;
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
            .info(apiInfo())
            .addServersItem(apiServer());
    }

    private Info apiInfo() {
        return new Info()
            .title("AshInTheFarNorth API")
            .description("최북단 사는 최재애쉬 API 문서입니다.")
            .version("v0.0.1");
    }

    private Server apiServer() {
        return new Server()
            .url(serverUrl);
    }
}
