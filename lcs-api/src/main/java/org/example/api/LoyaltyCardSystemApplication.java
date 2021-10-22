package org.example.api;

import org.example.api.swagger.EnableSwaggerDocumentation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwaggerDocumentation

public class LoyaltyCardSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoyaltyCardSystemApplication.class, args);
    }
}
