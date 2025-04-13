package com.moody.moody_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilita CSRF para testes (não recomendado em produção)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/registerUser").permitAll()  // Libera o endpoint de registro
                .anyRequest().permitAll()  // Libera todos os endpoints
            );  // Configura formulário de login padrão

        return http.build();
    }
    
}
