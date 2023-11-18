package com.elleined.springsecurity.controller.user;

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

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse save(@Valid @RequestBody UserRequest userRequest) {
        User user = userService.save(userRequest);
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getCredential().getEmail())
                .status(user.getStatus().name())
                .build();
    }
}
