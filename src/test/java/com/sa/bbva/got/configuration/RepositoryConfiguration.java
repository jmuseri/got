package com.sa.bbva.got.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "com.sa.bbva.got.model" })
@EnableJpaRepositories(basePackages = { "com.sa.bbva.got.repository" })
@EnableTransactionManagement
public class RepositoryConfiguration {
}
