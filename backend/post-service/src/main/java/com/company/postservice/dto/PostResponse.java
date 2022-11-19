package com.company.postservice.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PostResponse {
    private byte[] file;
    private String description;
    private long likes;
    private Set<String> tags;
}
