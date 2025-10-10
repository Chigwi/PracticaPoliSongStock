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

@Configuration
@EnableJpaRepositories(
        basePackages = "co.edu.poli.PolisongStock.RegistroPlaylist.Repository",  // ONLY inventory repositories
        entityManagerFactoryRef = "usuarioEntityManagerFactory"
    )

public class PlaylistDataSourceConfig {
	@Bean(name = "PlaylistDataSource")
    @ConfigurationProperties("spring.datasource.playlist")  // Binds to custom properties prefix
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "PlaylistEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("PlaylistDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(dataSource)
                .packages("co.edu.poli.PolisongStock.Playlist.Modelo")  // ONLY inventory entities
                .persistenceUnit("playlist")
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
