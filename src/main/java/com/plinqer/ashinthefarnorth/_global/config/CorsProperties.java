package com.plinqer.ashinthefarnorth._global.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cors")
public record CorsProperties(
    @Value("${cors.allowed-origins}")
    List<String> allowedOrigins
) {

}
