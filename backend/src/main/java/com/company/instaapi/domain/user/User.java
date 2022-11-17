package com.company.instaapi.domain.user;

import com.company.instaapi.domain.Post;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usr")
@Data
public class User {

    @Id
    private String id;

    @MapsId
    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserAuthInfo authInfo;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

}
