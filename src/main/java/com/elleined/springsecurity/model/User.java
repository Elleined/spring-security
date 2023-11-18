package com.elleined.springsecurity.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tbl_user")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @Column(
            name = "id",
            nullable = false,
            unique = true,
            updatable = false
    )
    private int id;

    @Embedded
    private Credential credential;

}