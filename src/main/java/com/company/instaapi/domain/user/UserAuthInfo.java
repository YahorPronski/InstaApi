package com.company.instaapi.domain.user;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class UserAuthInfo {
    @Id
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}