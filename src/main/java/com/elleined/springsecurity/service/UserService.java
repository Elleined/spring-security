package com.elleined.springsecurity.service;

import com.elleined.springsecurity.dto.user.UserRequest;
import com.elleined.springsecurity.model.User;

public interface UserService {
    User getByEmail(String email);

    User getById(int userId);

    User save(UserRequest userRequest);
}
