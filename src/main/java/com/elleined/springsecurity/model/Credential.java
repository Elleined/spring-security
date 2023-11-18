package com.elleined.springsecurity.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Embeddable
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credential {

    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @ElementCollection
    @CollectionTable(
            name = "tbl_user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    nullable = false
            )
    )
    private Set<String> roles;
}