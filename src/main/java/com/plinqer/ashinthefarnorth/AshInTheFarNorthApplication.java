package com.plinqer.ashinthefarnorth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
public class AshInTheFarNorthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AshInTheFarNorthApplication.class, args);
    }
}
