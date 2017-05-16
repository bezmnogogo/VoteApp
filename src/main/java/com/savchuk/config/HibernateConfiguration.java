//package com.savchuk.config;
//
//import org.flywaydb.core.Flyway;
//import org.flywaydb.core.api.MigrationInfoService;
//import org.flywaydb.core.api.MigrationVersion;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.annotation.TransactionManagementConfigurer;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Properties;
//
///**
// * Created by home on 14.11.16.
// */
//@Configuration
//@EnableJpaRepositories({"com.savchuk.dao.repository"})
//@EnableTransactionManagement
//@PropertySource({"classpath:application.properties"})
//public class HibernateConfiguration implements TransactionManagementConfigurer {
//
//	/*
//	 * JPA-Hibernate settings
//	 */
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    @DependsOn(value = {"flyway"})
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan("com.savchuk.dao.entities");
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(hibernateProperties());
//
//        return em;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
//        dataSource.setUrl(env.getProperty("spring.datasource.url"));
//        dataSource.setUsername(env.getProperty("spring.datasource.username"));
//        dataSource.setPassword(env.getProperty("spring.datasource.password"));
//        return dataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(emf);
//
//        return transactionManager;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//
//    @Bean(initMethod = "migrate")
//    public Flyway flyway() {
//        final Flyway flyway = new Flyway();
//        flyway.setDataSource(this.dataSource());
//
//        MigrationInfoService info = flyway.info();
//
//        //flyway.setInitOnMigrate(true);
//        if (env.getProperty("flyway.locations") == null)
//            return null;
//        flyway.setLocations(env.getProperty("flyway.locations").split(","));
//        flyway.setBaselineVersion(MigrationVersion.fromVersion(new String("1")));
//        //flyway.setLocations("db/migration/db.migration.mysql");
//        flyway.repair();
//        return flyway;
//    }
//
//    @SuppressWarnings("serial")
//    Properties hibernateProperties() {
//        return new Properties() {
//            {
//                setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
//                setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//                setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
//
//                setProperty("hibernate.globally_quoted_identifiers", "true");
//                setProperty("hibernate.connection.CharSet", "UTF-8");
//                setProperty("hibernate.connection.characterEncoding", "UTF-8");
//                setProperty("hibernate.connection.useUnicode", "true");
//            }
//        };
//    }
//
//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return transactionManager(entityManagerFactory().getNativeEntityManagerFactory());
//    }
//}