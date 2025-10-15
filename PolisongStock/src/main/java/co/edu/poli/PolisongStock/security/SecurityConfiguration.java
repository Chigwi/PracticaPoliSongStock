package co.edu.poli.PolisongStock.security;

import java.net.http.HttpRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

	@Bean
	public UserDetailsService detallesUsuarioServicio (PasswordEncoder encoder) {
		UserDetails admin = User
				.withUsername("admin0")
				.authorities("Basic", "Special")
				.roles("superusuario")
				.password(encoder.encode("1"))
				.build();
		UserDetails user = User
				.withUsername("alphaUser0")
				.authorities("Basic")
				.roles("basicusuario")
				.password(encoder.encode("2"))
				.build();
		return new InMemoryUserDetailsManager(admin, user);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf(csrf -> csrf.disable())  // CSRF is disabled
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/usuarios/crearusuarios").permitAll()  // Specific permit for this endpoint
                .requestMatchers("/api/usuarios/**").authenticated()  // Example: Require auth for other usuario paths
                .anyRequest().permitAll()  // General rule last
            )
            .httpBasic(Customizer.withDefaults());  // Or use formLogin if needed
        
        return httpSecurity.build();
    }
}
