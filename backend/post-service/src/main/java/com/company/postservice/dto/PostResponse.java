package com.company.postservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PostResponse {
    private String id;
    private String fileBase64;
    private String description;
    private String creationDate;
    private Integer likes;
    private Set<String> tags;
}
