package com.example.sportsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for secure password hashing
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/h2-console/**", "/signup", "/login").permitAll() // Allow H2 console access
                        .anyRequest().permitAll() // Require authentication for other endpoints
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF for H2 console
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.disable()) // Allow frames for H2 console
                )
                .logout(Customizer.withDefaults());

        return http.build();
    }
}
