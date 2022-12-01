package com.company.userservice.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "uzer")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Column(name = "post_id")
    private Set<Long> postIds;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Column(name = "follower_id")
    private Set<Long> followerIds;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Column(name = "following_id")
    private Set<Long> followingIds;

}
