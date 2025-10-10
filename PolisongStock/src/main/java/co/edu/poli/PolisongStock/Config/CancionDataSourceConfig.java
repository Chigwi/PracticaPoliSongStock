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

@Configuration
@EnableJpaRepositories(
    basePackages = "co.edu.poli.PolisongStock.Cancion.Repository",
    entityManagerFactoryRef = "cancionEntityManagerFactory"
    // No transactionManagerRef - Spring auto-creates JpaTransactionManager for this unit
)
public class CancionDataSourceConfig {
    
    @Bean(name = "CancionDataSource")
    @ConfigurationProperties("spring.datasource.cancion")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "cancionEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("CancionDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(dataSource)
                .packages("co.edu.poli.PolisongStock.Cancion.Modelo")
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
