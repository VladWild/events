package org.example.config.derby;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"org.example.repositories.db.events.derby"},
        entityManagerFactoryRef = "derbyEntityManager",
        transactionManagerRef = "derbyTransactionManager")
@EntityScan(basePackages = "org.example.model.db.events.derby")
public class EventsDbDerbyConfig {

    @Bean
    @ConfigurationProperties(prefix="spring.derby-datasource")
    public DataSource productDataSource() {
        return DataSourceBuilder.create().build();
    }
}
