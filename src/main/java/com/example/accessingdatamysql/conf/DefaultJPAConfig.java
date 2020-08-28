package com.example.accessingdatamysql.conf;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;

/**
 * @author caiwl
 * @date 2020/4/14 10:11
 */
@Configuration
@EnableJpaRepositories(
        basePackages = {"com.example.accessingdatamysql.first"},
        entityManagerFactoryRef = "firstEntityManagerFactory",
        transactionManagerRef = "firstTransactionManager")
public class DefaultJPAConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.first")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.first.configuration")
    public HikariDataSource firstDataSource() {
        return firstDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "firstEntityManager")
    public EntityManager customEntityManager(EntityManagerFactoryBuilder builder) {
        return customEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "firstEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean customEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(firstDataSource())
                .packages("com.example.accessingdatamysql.first")
                .persistenceUnit("firstPersistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = "firstTransactionManager")
    public PlatformTransactionManager customTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(customEntityManagerFactory(builder).getObject());
    }
}
