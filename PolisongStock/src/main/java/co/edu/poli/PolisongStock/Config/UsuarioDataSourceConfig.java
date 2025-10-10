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
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableJpaRepositories(
        basePackages = "co.edu.poli.PolisongStock.Usuario.Repository",  // ONLY inventory repositories
        entityManagerFactoryRef = "usuarioEntityManagerFactory"
    )

public class UsuarioDataSourceConfig {

	@Bean(name = "UsuarioDataSource")
    @ConfigurationProperties("spring.datasource.usuario")  // Binds to custom properties prefix
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "usuarioEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("UsuarioDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(dataSource)
                .packages("co.edu.poli.PolisongStock.Usuario.Modelo")  // ONLY inventory entities
                .persistenceUnit("usuario")
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
}
