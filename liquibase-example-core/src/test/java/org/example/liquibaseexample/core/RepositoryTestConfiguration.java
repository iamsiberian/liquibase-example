package org.example.liquibaseexample.core;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@AutoConfigurationPackage
@ComponentScan(basePackages = "org.example.liquibaseexample.core.repository")
@EntityScan(basePackages = "org.example.liquibaseexample.core.domain")
public class RepositoryTestConfiguration {
}
