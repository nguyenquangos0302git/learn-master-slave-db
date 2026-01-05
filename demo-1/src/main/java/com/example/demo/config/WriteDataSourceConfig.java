package com.example.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.repository.write",
        entityManagerFactoryRef = "writeEntityManagerFactory",
        transactionManagerRef = "writeTransactionManager"
)
public class WriteDataSourceConfig {

    @Value("${spring.datasource.master.url}")
    private String urlMaster;

    @Value("${spring.datasource.master.username}")
    private String userNameMaster;

    @Value("${spring.datasource.master.password}")
    private String passwordMaster;

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.master")
    public DataSource writeDataSource() {
        return DataSourceBuilder
                .create()
                .url(urlMaster)
                .username(userNameMaster)
                .password(passwordMaster)
                .build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean writeEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(writeDataSource())
                .packages("com.example.demo.entity")
                .persistenceUnit("write")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager writeTransactionManager(
            @Qualifier("writeEntityManagerFactory")
            EntityManagerFactory emf) {

        return new JpaTransactionManager(emf);
    }

}
