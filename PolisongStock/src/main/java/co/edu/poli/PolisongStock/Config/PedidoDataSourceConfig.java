package co.edu.poli.PolisongStock.Config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
    basePackages = "co.edu.poli.PolisongStock.RegistroPedidos.repository",
    entityManagerFactoryRef = "pedidosEntityManagerFactory",
    transactionManagerRef = "pedidosTransactionManager"
)
public class PedidoDataSourceConfig {

    @Primary
    @Bean(name = "pedidoDataSource")
    // TEMP: Comment out @ConfigurationProperties for hardcoded test
    // @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        // HARDCODED TEST: Replace with your Supabase details
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:6543/postgres?sslmode=require");
        config.setUsername("postgres.qitkxnnjxhwrryqhctac");
        config.setPassword("Servidor123");
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTestQuery("SELECT 1");
        config.setConnectionTimeout(30000);
        config.setValidationTimeout(5000);
        config.setAutoCommit(false);
        

        // Evitar prepared statements del lado servidor que causan colisiones en Postgres
        config.addDataSourceProperty("prepareThreshold", "0");
        config.addDataSourceProperty("preferQueryMode", "simple");

        // Dejar autoCommit por defecto (true) para evitar errores al cambiar auto-commit durante DDL
        config.setAutoCommit(false);


        DataSource ds = new HikariDataSource(config);
        System.out.println("HARDCODED DataSource created with URL: " + config.getJdbcUrl());  // Confirm in console
        return ds;
    }

    @Primary
    @Bean(name = "pedidosEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("pedidoDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(dataSource)
                .packages("co.edu.poli.PolisongStock.RegistroPedidos.modelo")
                .persistenceUnit("pedido")
                .build();
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    @Bean(name = "pedidosTransactionManager")
    public PlatformTransactionManager pedidoTransactionManager(
            @Qualifier("pedidosEntityManagerFactory") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }
}
