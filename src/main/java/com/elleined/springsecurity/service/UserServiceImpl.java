package com.elleined.springsecurity.service;

import com.elleined.springsecurity.dto.user.UserRequest;
import com.elleined.springsecurity.model.Credential;
import com.elleined.springsecurity.model.User;
import com.elleined.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public User getByEmail(String email) {
        return userRepository.findAll().stream()
                .filter(user -> user.getCredential().getEmail().equals(email))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public User getById(int userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    public User save(UserRequest userRequest) {
        Set<String> roles = new HashSet<>();
        roles.add("USER");

        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        User user = User.builder()
                .credential(Credential.builder()
                        .email(userRequest.getEmail())
                        .password(encodedPassword)
                        .roles(roles)
                        .build())
                .build();

        userRepository.save(user);
        log.debug("User with id of {} saved successfully", user.getId());
        return user;
    }

    @Override
    public User save(UserRequest userRequest, String... roles) {
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        User user = User.builder()
                .credential(Credential.builder()
                        .email(userRequest.getEmail())
                        .password(encodedPassword)
                        .roles(new HashSet<>(Arrays.asList(roles)))
                        .build())
                .build();

        userRepository.save(user);
        log.debug("User with id of {} saved successfully", user.getId());
        return user;
    }
}
