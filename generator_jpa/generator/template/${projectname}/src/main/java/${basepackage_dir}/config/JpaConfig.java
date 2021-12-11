package com.angogo.rpa.console.system.api.config;


import com.angogo.rpa.console.common.repository.factory.BaseRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Jpa配置
 *
 * @Author: Aoheng
 * @Date: 2021/4/16 14:04
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "systemEntityManagerFactory", transactionManagerRef = "systemTransactionManager", basePackages = {"${basepackage}.repository"}, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class JpaConfig {
    @Bean(name = "systemDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource systemDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "systemEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("systemDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("${basepackage}.entity")
                .persistenceUnit("basicUnit")
                .build();
    }

    @Bean(name = "systemTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("systemEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
