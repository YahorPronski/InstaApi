package com.company.postservice.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PostRequest {
    private String description;
    private Set<String> tags;
}
