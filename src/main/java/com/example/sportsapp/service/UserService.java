package com.example.sportsapp.service;

import com.example.sportsapp.model.User;
import com.example.sportsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void save(User users) {
        // Encrypt the password before saving it
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepository.save(users);
    }

    public String registerUser(User users) {
        Optional<User> existingUser = findByEmail(users.getEmail());
        if (existingUser.isPresent()) {
            return "Email already taken";
        }
        save(users); // Save the user after password encoding
        return "User registered successfully";
    }

    public void saveAdminUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ADMIN"); // Set default role
        userRepository.save(user);
    }
}
