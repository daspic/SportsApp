package com.example.sportsapp.config;

import com.example.sportsapp.model.User;
import com.example.sportsapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(UserService userService) {
        return args -> {
            // Check if the admin user already exists
            if (userService.findByEmail("admin@example.com").isEmpty()) {
                // Create and save the admin user
                User adminUser = new User();
                adminUser.setName("Admin User");
                adminUser.setContactInfo("contact info");
                adminUser.setEmail("admin@example.com");
                adminUser.setPassword("admin123"); // Default password
                adminUser.setRole("ADMIN");

                userService.saveAdminUser(adminUser);
                System.out.println("Admin user created: admin@example.com / admin123");
            }
        };
    }
}

