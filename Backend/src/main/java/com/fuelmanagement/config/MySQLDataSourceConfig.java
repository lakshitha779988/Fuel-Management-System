package com.fuelmanagement.config;

import jakarta.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.fuelmanagement.repository.mysql",
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager"
)
public class MySQLDataSourceConfig {

    @Primary
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.mysql.datasource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/fuel_managment_system?allowPublicKeyRetrieval=true&useSSL=false")
                .username("root")
                .password("778996508Lk#")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Primary
    @Bean(name = "mysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mysqlDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.fuelmanagement.model.entity.mysql")
                .persistenceUnit("mysql")
                .build();
    }

    @Primary
    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(
            @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
