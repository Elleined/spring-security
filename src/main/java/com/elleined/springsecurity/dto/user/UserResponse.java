package com.elleined.springsecurity.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private int id;

    private String email;
    private String status;
}
