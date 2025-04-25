package com.admin.adminProject.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.admin.adminProject.repository.db2",
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef="db2TransactionManager"
)
public class Db2Config {


    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entitymanagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("db2DataSource") DataSource dataSource
    ){
        return builder
                .dataSource(dataSource)
                .packages("com.admin.adminProject.model.db2")
                .persistenceUnit("db2")
                .build();
    }
    @Bean(name = "db2TransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("db2EntityManagerFactory") EntityManagerFactory emf
    ){
        return new JpaTransactionManager(emf);
    }

    @Bean(name = "db2DataSource")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/admin_project");
        config.setUsername("admin");
        config.setPassword("admin123");
        config.setDriverClassName("org.postgresql.Driver");
        return new HikariDataSource(config);
    }
}
