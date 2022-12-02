package com.company.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @Column(columnDefinition = "text")
    private String description;

    @Builder.Default
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate = new Date();

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ElementCollection
    @Column(name = "liked_user_id")
    private Set<Long> likedUserIds;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Comment> comments;

}