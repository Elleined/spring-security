package com.elleined.springsecurity.controller.admin;

import com.elleined.springsecurity.dto.user.UserResponse;
import com.elleined.springsecurity.model.User;
import com.elleined.springsecurity.service.AdminService;
import com.elleined.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admins/users/{userId}")
public class AdminController {
    private final AdminService adminService;
    private final UserService userService;

    @PatchMapping("/approved")
    public UserResponse approveUser(@PathVariable("userId") int userId) {
        User user = userService.getById(userId);
        adminService.approvedUser(user);

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getCredential().getEmail())
                .status(user.getStatus().name())
                .build();
    }
}
