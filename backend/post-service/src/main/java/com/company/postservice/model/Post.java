package com.company.postservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_src", nullable = false)
    private String imageSrc;

    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate = new Date();

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ElementCollection
    @Column(name = "liked_user_id")
    private Set<Long> likedUserIds;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

}