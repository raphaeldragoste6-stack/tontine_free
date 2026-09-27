package raphael.tontinepro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Autorise l'accès public à la racine, aux fichiers statiques et au login Google
                        .requestMatchers("/", "/index.css", "/css/**", "/js/**", "/images/**").permitAll()
                        // Toutes les autres pages nécessitent d'être connecté
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        // Redirige vers /tontines une fois connecté avec Google
                        .defaultSuccessUrl("/dashboard", true)
                );

        return http.build();
    }
}