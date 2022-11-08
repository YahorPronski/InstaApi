package com.company.instaapi.domain.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class UserAuthInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @OneToOne(mappedBy = "authInfo")
    private User user;
}