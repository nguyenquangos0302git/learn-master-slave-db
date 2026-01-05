package com.example.demo.config;

import com.example.demo.enums.DbType;
import jakarta.persistence.EntityManagerFactory;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.demo.repository")
public class DataSourceConfig {

    @Value("${spring.datasource.master.url}")
    private String urlMaster;

    @Value("${spring.datasource.master.username}")
    private String userNameMaster;

    @Value("${spring.datasource.master.password}")
    private String passwordMaster;

    @Value("${spring.datasource.slave.url}")
    private String urlSlave;

    @Value("${spring.datasource.slave.username}")
    private String userNameSlave;

    @Value("${spring.datasource.slave.password}")
    private String passwordSlave;

    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        System.out.println("Abc" + urlMaster);
        return DataSourceBuilder
                .create()
                .url(urlMaster)
                .username(userNameMaster)
                .password(passwordMaster)
                .build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder
                .create()
                .url(urlSlave)
                .username(userNameSlave)
                .password(passwordSlave)
                .build();
    }

    @Bean
    @Primary
    public DataSource routingDataSource() {

        Map<Object, Object> targets = new HashMap<>();
        targets.put(DbType.MASTER, masterDataSource());
        targets.put(DbType.SLAVE, slaveDataSource());

        ReplicationRoutingDataSource routing = new ReplicationRoutingDataSource();
        routing.setDefaultTargetDataSource(masterDataSource());
        routing.setTargetDataSources(targets);

        return routing;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(routingDataSource())
                .packages("com.example.demo.entity")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory emf) {

        return new JpaTransactionManager(emf);
    }

}
