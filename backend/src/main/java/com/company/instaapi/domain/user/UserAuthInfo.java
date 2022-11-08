package com.company.instaapi.domain.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Data
public class UserAuthInfo {

    @Id
    private String id = UUID.randomUUID().toString();

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @OneToOne(mappedBy = "authInfo")
    private User user;
}