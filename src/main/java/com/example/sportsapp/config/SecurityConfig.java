package com.example.sportsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig  { // extends WebSecurityConfigurerAdapter

    // Define the password encoder as a bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define the SecurityFilterChain for HTTP security configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/signup/**", "/login/**", "/h2-console/**").permitAll() // Allow access to signup and login endpoints
                        .anyRequest().permitAll() // Restrict access to other endpoints
                )
                .formLogin(withDefaults()) // Use default login page and behavior
                .logout(withDefaults());  // Use default logout behavior

        return http.build();
    }
}
