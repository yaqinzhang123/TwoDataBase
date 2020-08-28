package com.example.accessingdatamysql.conf;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;

/**
 * @author caiwl
 * @date 2020/4/14 10:13
 */
@Configuration
@EnableJpaRepositories(
        basePackages = {"com.example.accessingdatamysql.second"},
        entityManagerFactoryRef = "secondEntityManagerFactory",
        transactionManagerRef = "secondTransactionManager")
public class SecondJPAConfig {

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.second")
    public HikariDataSource secondDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "secondEntityManager")
    public EntityManager customEntityManager(EntityManagerFactoryBuilder builder) {
        return customEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "secondEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean customEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondDataSource())
                .packages("com.example.accessingdatamysql.second")
                .persistenceUnit("secondPersistenceUnit")
                .build();
    }

    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager customTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(customEntityManagerFactory(builder).getObject());
    }
}
