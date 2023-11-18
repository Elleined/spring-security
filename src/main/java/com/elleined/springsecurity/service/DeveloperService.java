package com.elleined.springsecurity.service;

import com.elleined.springsecurity.dto.user.UserRequest;
import com.elleined.springsecurity.model.Credential;
import com.elleined.springsecurity.model.User;

import java.util.Set;

public interface DeveloperService {
    User save(UserRequest userRequest, Set<Credential.Role> roles);
    User save(UserRequest userRequest, Set<Credential.Role> roles, User.Status status);
}
