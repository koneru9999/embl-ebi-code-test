package uk.ac.ebi.codetest.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Application configuration
 *
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

    /**
     * Default behaviour of Spring's event ecosystem is synchronous.
     * <p>
     * With this multi caster present in the context, event ecosystem will act as asynchronous.
     *
     * @return multi cast event publisher
     */
    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster
                = new SimpleApplicationEventMulticaster();

        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }

    /**
     * Configures ObjectMapper with Include.NON_NULL using setSerializationInclusion() that ignore Null values globally
     * for every class.
     *
     * @return object mapper bean instance
     */
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .serializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
