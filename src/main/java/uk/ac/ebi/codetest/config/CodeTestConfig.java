package uk.ac.ebi.codetest.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Venkaiah Chowdary Koneru
 */
@Configuration
@ComponentScan(basePackages = {
        "uk.ac.ebi.codetest.service",
        "uk.ac.ebi.codetest.mappers",
        "uk.ac.ebi.codetest.controller"
})
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "uk.ac.ebi.codetest.repository")
public class CodeTestConfig {
}
