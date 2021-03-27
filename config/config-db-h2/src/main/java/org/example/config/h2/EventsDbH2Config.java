package org.example.config.h2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(basePackages = {"org.example.repositories.db.events.h2"},
        entityManagerFactoryRef = "entityManager",
        transactionManagerRef = "transactionManager")
public class EventsDbH2Config {

    private static final String HIBERNATE_DDL = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    @Bean(name = "dataSource")
    @Primary
    //@ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().url("jdbc:h2:mem:test-db-h2").driverClassName("org.h2.Driver").build();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .persistenceUnit("events-h2")
                .packages("org.example.model.db.events.h2")
                .properties(getHibernateProperties())
                .build();
    }

    @Bean(name = "entityManager")
    public EntityManager entityManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Map<String, String> getHibernateProperties() {
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.hbm2ddl.auto", "update");
        map.put("hibernate.format_sql", "true");
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return map;
    }
}
