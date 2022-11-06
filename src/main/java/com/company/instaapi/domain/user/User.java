package com.company.instaapi.domain.user;

import com.company.instaapi.domain.Post;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name = "usr")
@Data
public class User {

    @Id
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserAuthInfo authInfo;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;

}
