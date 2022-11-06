package com.company.instaapi.domain;

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

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;

}