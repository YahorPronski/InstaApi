package com.company.instaapi.domain;

import com.company.instaapi.domain.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate = new Date();

    @Lob
    @NotEmpty
    private byte[] image;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags = new HashSet<>();

}