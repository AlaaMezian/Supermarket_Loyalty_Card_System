package org.example.lcs.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerDocumentation {

    //Note SLCS stands for Supermarket Loyalty Card System
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("SLCS APi")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Supermarket Loyalty Card System","http://www.SLCS.com","info@SLCS.com");
        return new ApiInfoBuilder()
                .title("SLCS API")
                .description("SLCS API Description")
                .license("license")
                .version("1.0")
                .contact(contact)
                .licenseUrl("licenseURl")
                .build();
    }
}