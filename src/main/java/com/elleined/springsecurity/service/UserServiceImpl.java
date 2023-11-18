package com.elleined.springsecurity.service;

import com.elleined.springsecurity.dto.user.UserRequest;
import com.elleined.springsecurity.model.Credential;
import com.elleined.springsecurity.model.User;
import com.elleined.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getByEmail(String email) {
        return userRepository.findAll().stream()
                .filter(user -> user.getCredential().getEmail().equals(email))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public User save(UserRequest userRequest) {
        Set<String> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add("USER");

        User user = User.builder()
                .credential(Credential.builder()
                        .email(userRequest.getEmail())
                        .password(userRequest.getPassword())
                        .authorities(grantedAuthorities)
                        .build())
                .build();

        userRepository.save(user);
        log.debug("User with id of {} saved successfully", user.getId());
        return user;
    }
}
