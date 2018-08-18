package uk.ac.ebi.codetest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Venkaiah Chowdary Koneru
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select() //selecting `ApiSelectorBuilder`, which provides a way to control the endpoints exposed by Swagger
                .apis(RequestHandlerSelectors.basePackage("uk.ac.ebi.codetest.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}