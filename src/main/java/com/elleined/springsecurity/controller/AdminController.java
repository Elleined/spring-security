package com.elleined.springsecurity.controller;

import com.elleined.springsecurity.dto.user.UserRequest;
import com.elleined.springsecurity.dto.user.UserResponse;
import com.elleined.springsecurity.model.User;
import com.elleined.springsecurity.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/users")
public class AdminController {
    private final UserService userService;

    @PostMapping
    public UserResponse saveUser(@Valid @RequestBody UserRequest userRequest) {
        User user = userService.save(userRequest);
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getCredential().getEmail())
                .build();
    }
}
