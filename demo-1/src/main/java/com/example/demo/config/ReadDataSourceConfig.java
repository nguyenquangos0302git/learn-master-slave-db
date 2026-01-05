package com.example.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.repository.read",
        entityManagerFactoryRef = "readEntityManagerFactory",
        transactionManagerRef = "readTransactionManager"
)
public class ReadDataSourceConfig {

    @Value("${spring.datasource.slave.url}")
    private String urlSlave;

    @Value("${spring.datasource.slave.username}")
    private String userNameSlave;

    @Value("${spring.datasource.slave.password}")
    private String passwordSlave;

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource readDataSource() {
        return DataSourceBuilder
                .create()
                .url(urlSlave)
                .username(userNameSlave)
                .password(passwordSlave)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(readDataSource())
                .packages("com.example.demo.entity")
                .persistenceUnit("read")
                .build();
    }

    @Bean
    public PlatformTransactionManager readTransactionManager(
            @Qualifier("readEntityManagerFactory")
            EntityManagerFactory emf) {

        return new JpaTransactionManager(emf);
    }

}
