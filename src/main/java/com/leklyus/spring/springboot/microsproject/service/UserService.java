package com.leklyus.spring.springboot.microsproject.service;

import com.leklyus.spring.springboot.microsproject.model.User;
import com.leklyus.spring.springboot.microsproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        log.info("Create user: {}", user);
        return userRepository.save(user);
    }

    public Optional<User> getUser(Long id) {
        log.info("User findById {}", id);
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User newUser) {
        log.info("Update user id: {}, new user: {}", id, newUser);
        return userRepository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow();
    }

    public void deleteUser(Long id) {
        log.info("Delete user id: {}", id);
        userRepository.deleteById(id);
    }
}