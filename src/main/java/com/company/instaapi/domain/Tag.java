package com.company.instaapi.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
public class Tag {
    @Id
    @NotBlank
    private String tag;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Post> posts;
}