package com.elleined.springsecurity.controller.user;

import com.elleined.springsecurity.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/secured/users/{userId}")
public class SecuredUserController {

    private final UserService userService;


    @GetMapping("/password")
    public String getPassword(@PathVariable("userId") int userId) {
        return userService.getById(userId).getCredential().getPassword();
    }
}
