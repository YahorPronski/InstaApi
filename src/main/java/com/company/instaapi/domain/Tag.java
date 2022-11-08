package com.company.instaapi.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Tag {
    @Id
    @NotBlank
    private String tag;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();
}