package co.edu.poli.PolisongStock.security;

import java.net.http.HttpRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
		return new InMemoryUserDetailsManager(admin);
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        
            .csrf(csrf -> csrf.disable())  // CSRF is disabled
<<<<<<< HEAD
            .authorizeHttpRequests(authorize -> {
                authorize.requestMatchers("/api/usuarios/crearusuarios").permitAll();  // Specific permit for this endpoint
                //authorize.requestMatchers("/api/usuarios/**").authenticated();  // Example: Require auth for other usuario paths
                
                //Autorizar vista libre
                
                authorize.requestMatchers(HttpMethod.GET, "/api/canciones").permitAll();
                authorize.requestMatchers(HttpMethod.GET, "/api/playlist").permitAll();

                authorize.anyRequest().authenticated();  // General rule last
            })
            .addFilterBefore(new BasicAuthenticationFilter(authenticationManager(httpSecurity)), UsernamePasswordAuthenticationFilter.class);  // Or use formLogin if needed
=======
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/canciones/formato/").permitAll()  // Specific permit for this endpoint
                .requestMatchers("/api/usuarios/**").authenticated()  // Example: Require auth for other usuario paths
                .anyRequest().permitAll()  // General rule last
            )
            .httpBasic(Customizer.withDefaults());  // Or use formLogin if needed
>>>>>>> branch 'master' of https://github.com/Chigwi/PracticaPoliSongStock
        
        return httpSecurity.build();
//tetas
    }
}
