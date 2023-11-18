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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false,
            unique = true,
            updatable = false
    )
    private int id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private Credential credential;

    public enum Status {
        APPROVED,
        NOT_APPROVED
    }
}
