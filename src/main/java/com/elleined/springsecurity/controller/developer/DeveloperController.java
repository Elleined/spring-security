package com.elleined.springsecurity.controller.developer;

import com.elleined.springsecurity.dto.user.UserRequest;
import com.elleined.springsecurity.dto.user.UserResponse;
import com.elleined.springsecurity.model.User;
import com.elleined.springsecurity.service.DeveloperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.elleined.springsecurity.model.Credential.Role;

@RestController
@RequestMapping("/developers")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;
    @PostMapping("/save-user-with-custom-roles")
    public UserResponse save(@Valid @RequestPart("userRequest") UserRequest userRequest,
                             @RequestParam("roles") Set<Role> roles) {
        User user = developerService.save(userRequest, roles);
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getCredential().getEmail())
                .status(user.getStatus().name())
                .build();
    }

    @PostMapping("/save-user-with-custom-status")
    public UserResponse save(@Valid @RequestPart("userRequest") UserRequest userRequest,
                             @RequestParam("roles") Set<Role> roles,
                             @RequestParam("status") User.Status status) {
        User user = developerService.save(userRequest, roles, status);
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getCredential().getEmail())
                .status(user.getStatus().name())
                .build();
    }
}
