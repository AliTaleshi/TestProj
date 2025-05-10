package com.example.TestProj.service.impl;

import com.example.TestProj.dto.RegisterRequest;
import com.example.TestProj.entity.User;
import com.example.TestProj.enums.Role;
import com.example.TestProj.repository.UserRepository;
import com.example.TestProj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER);

        log.info("User registered with request: {}", request);
        return userRepository.save(user);
    }

    @Override
    public User registerAdmin(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_ADMIN);

        log.info("Admin registered with request: {}", request);
        return userRepository.save(user);
    }
}
