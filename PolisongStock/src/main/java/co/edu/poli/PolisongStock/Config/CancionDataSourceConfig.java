package co.edu.poli.PolisongStock.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = "co.edu.poli.PolisongStock.RegistroCancion.repository",
    entityManagerFactoryRef = "cancionEntityManagerFactory"
    // No transactionManagerRef - Spring auto-creates JpaTransactionManager for this unit
)
public class CancionDataSourceConfig {
    
	@Bean(name = "cancionDataSource")
    public DataSource dataSource() {
        // HARDCODED TEST: Replace with your Supabase details
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:6543/postgres?sslmode=require");
        System.out.println("sapopinga!");
        config.setUsername("postgres.ejbfypdljlyopvcxvbno");
        config.setPassword("Servidor123");
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTestQuery("SELECT 1");
        config.setConnectionTimeout(30000);
        config.setValidationTimeout(5000);
        config.setAutoCommit(false);

        DataSource ds = new HikariDataSource(config);
        System.out.println("HARDCODED DataSource created with URL: " + config.getJdbcUrl());  // Confirm in console
        return ds;
    }


    @Bean(name = "cancionEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("cancionDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(dataSource)
                .packages("co.edu.poli.PolisongStock.RegistroCancion.Modelo")
                .persistenceUnit("cancion")
                .build();
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }

    // Transaction manager is auto-configured by Spring Boot - no bean needed!
}
