package org.example.lcs;

import org.example.lcs.swagger.EnableSwaggerDocumentation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableSwaggerDocumentation
@EnableJpaAuditing
public class LoyaltyCardSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoyaltyCardSystemApplication.class, args);
    }
}
