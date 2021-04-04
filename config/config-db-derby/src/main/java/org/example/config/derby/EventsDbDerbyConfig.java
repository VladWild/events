package org.example.config.derby;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"org.example.repositories.db.events.derby"},
        entityManagerFactoryRef = "entityManagerFactoryDerby",
        transactionManagerRef = "transactionManagerDerby")
public class EventsDbDerbyConfig {

    @Bean(name = "dataSourceDerby")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:derby:target/derbydb;create=true")
                .driverClassName("org.hibernate.dialect.DerbyDialect")
                .build();
    }

    @Bean(name = "entityManagerFactoryDerby")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSourceDerby") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .persistenceUnit("events-derby")
                .packages("org.example.model.db.events.derby")
                .properties(getHibernateProperties())
                .build();
    }

    @Bean(name = "entityManagerDerby")
    public EntityManager entityManager(
            @Qualifier("entityManagerFactoryDerby") EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean(name = "transactionManagerDerby")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactoryDerby") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Map<String, String> getHibernateProperties() {
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.hbm2ddl.auto", "update");
        map.put("hibernate.format_sql", "true");
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        return map;
    }
}
