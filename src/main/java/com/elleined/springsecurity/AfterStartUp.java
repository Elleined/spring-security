package com.elleined.springsecurity;

import com.elleined.springsecurity.model.Credential;
import com.elleined.springsecurity.model.User;
import com.elleined.springsecurity.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.elleined.springsecurity.model.Credential.builder;

@Component
@Transactional
@RequiredArgsConstructor
public class AfterStartUp {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {
        if (userRepository.existsById(1)) return;
        Set<Credential.Role> roles = new HashSet<>(List.of(Credential.Role.values()));
        String encodedPassword = passwordEncoder.encode("password");
        User developer = User.builder()
                .id(1)
                .credential(builder()
                        .email("developer@gmail.com")
                        .password(encodedPassword)
                        .roles(roles)
                        .build())
                .status(User.Status.APPROVED)
                .build();
        userRepository.save(developer);
    }
}
