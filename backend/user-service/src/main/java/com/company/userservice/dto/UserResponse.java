package com.company.userservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String username;

    private Integer postsCount;
    private Integer followersCount;
    private Integer followingCount;
}
