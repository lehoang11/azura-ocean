package com.azura.lisa.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mysqlEntityManager",
        transactionManagerRef = "mysqlTransactionManager",
        basePackages = {
                "com.azura.lisa.repository",
                "com.adonisle.auth.repository",
                "com.azura.tutorial.repository"
        }
)
public class MysqlDbConfig extends DbConfig {

    private String[] ENTITYMANAGER_PACKAGES_TO_SCAN = {"com.azura.lisa.model", "com.adonisle.auth.model","com.azura.tutorial.model",};

    {
        DB_URL = "mysql.db.url";
        DB_USER = "mysql.db.user";
        DB_PASSWORD = "mysql.db.password";
    }

    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource() {
        DriverManagerDataSource mysqlDataSource = new DriverManagerDataSource();
        mysqlDataSource.setDriverClassName( env.getProperty(DB_DRIVER));
        mysqlDataSource.setUrl(env.getProperty(DB_URL));
        mysqlDataSource.setUsername(env.getProperty(DB_USER));
        mysqlDataSource.setPassword(env.getProperty(DB_PASSWORD));
        return mysqlDataSource;
    }

    @Bean
    public PlatformTransactionManager mysqlTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mysqlEntityManager().getObject());
        return transactionManager;
    }

    @Bean(name = "mysqlEntityManager")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mysqlDataSource());
        em.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        em.setJpaVendorAdapter(vendorAdaptor());
        em.setPersistenceUnitName("mysql");
        return em;
    }
}
