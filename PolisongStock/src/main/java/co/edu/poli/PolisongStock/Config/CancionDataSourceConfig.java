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
import org.springframework.orm.jpa.EntityManagerFactory;  // <-- ADD THIS LINE
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;



@Configuration
@EnableJpaRepositories(
	    basePackages = "co.edu.poli.PolisongStock.Cancion.Repository",  // ONLY inventory repositories
	    entityManagerFactoryRef = "cancionEntityManagerFactory",
	    transactionManagerRef = "cancionTransactionManager"
	)
public class CancionDataSourceConfig {
	@Bean(name = "CancionDataSource")
    @ConfigurationProperties("spring.datasource.cancion")  // Binds to custom properties prefix
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "cancionEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("CancionDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(dataSource)
                .packages("co.edu.poli.PolisongStock.Cancion.Modelo")  // ONLY inventory entities
                .persistenceUnit("cancion")
                .build();
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    @Bean(name = "cancionTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("cancionEntityManagerFactory") org.springframework.orm.jpa.EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "validate");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }
	

}
