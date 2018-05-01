package se.jaw.jaxrs.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        // Alternative registration of resources and providers.
        // All classes annotated with either @Path or @Provider will be registered in Jersey (JAX-RS)
        packages("se.jaw.jaxrs.resource");
    }

    @Bean
    public ObjectMapper objectMapper() {
        // Adds support for Java 8 parameter preservation
        // Note: In IntelliJ, set -parameters option in Build, Execution, Deployment > Compiler > Java Compiler >
        // Additional command line parameters
        // Remember to rebuild project!
        return new ObjectMapper().registerModule(new ParameterNamesModule());
    }
}
