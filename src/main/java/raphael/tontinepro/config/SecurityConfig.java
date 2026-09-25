package raphael.tontinepro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactive CSRF pour tester facilement avec Postman
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // Autorise tes routes API
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}