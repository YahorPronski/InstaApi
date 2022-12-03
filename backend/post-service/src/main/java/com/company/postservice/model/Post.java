package com.company.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Column(name = "creation_date")
    private Date creationDate = new Date();

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Column(name = "liked_user_id")
    private Set<Long> likedUserIds;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Comment> comments;

}