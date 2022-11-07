package com.company.instaapi.domain;

import com.company.instaapi.domain.user.User;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "creation_date")
    private Date creationDate;

    @Lob
    private byte[] image;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;

}