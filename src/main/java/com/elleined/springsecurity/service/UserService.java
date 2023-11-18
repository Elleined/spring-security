package com.elleined.springsecurity.service;

import com.elleined.springsecurity.model.User;

public interface UserService {
    User getByEmail(String email);
}
