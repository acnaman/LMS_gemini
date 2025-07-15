package com.example.backend.application.service;

import com.example.backend.domain.model.User;
import com.example.backend.domain.repository.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return Optional.empty();
    }

    public User updateUser(User user) {
        return null;
    }

    public void deleteUser(Long id) {
        // Implementation will go here
    }
}
