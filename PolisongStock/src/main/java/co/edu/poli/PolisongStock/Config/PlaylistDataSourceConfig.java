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

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = "co.edu.poli.PolisongStock.RegistroPlaylist.repository",
    entityManagerFactoryRef = "PlaylistEntityManagerFactory",
    transactionManagerRef = "PlaylistTransactionManager"
)
public class PlaylistDataSourceConfig {

	@Bean(name = "playlistDataSource")
	public DataSource dataSource() {

	    HikariConfig config = new HikariConfig();
	    config.setJdbcUrl("jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?sslmode=require");
	    config.setUsername("postgres.fqrjhkplaxmoqkvfdfus");
	    config.setPassword("Servidor123");
	    config.setDriverClassName("org.postgresql.Driver");

	    // *** CONFIGURACIÓN SEGURA PARA SUPABASE ***
	    config.setMaximumPoolSize(3);   // antes 10 → saturaba Postgres
	    config.setMinimumIdle(1);       // antes 5
	    config.setIdleTimeout(30000);   // 30s
	    config.setMaxLifetime(1800000); // 30 min
	    config.setConnectionTimeout(30000);

	    // Supabase trabaja mejor con autocommit
	    config.setAutoCommit(false);

	    // Ayuda a detectar fugas de conexiones
	    config.setLeakDetectionThreshold(20000);

	    // Evitar queries complejas en pooler
	    config.addDataSourceProperty("prepareThreshold", "0");
	    config.addDataSourceProperty("preferQueryMode", "simple");

	    return new HikariDataSource(config);
	}


    @Bean(name = "PlaylistEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("playlistDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(dataSource)
                .packages("co.edu.poli.PolisongStock.RegistroPlaylist.modelo") // corregido a 'modelo'
                .persistenceUnit("playlist")
                .build();
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    @Bean(name = "PlaylistTransactionManager")
    public PlatformTransactionManager PlaylistTransactionManager(
            @Qualifier("PlaylistEntityManagerFactory") LocalContainerEntityManagerFactoryBean emf) {
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
