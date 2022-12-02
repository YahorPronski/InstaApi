package com.company.postservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class PostRequest {

    @NotBlank
    private String fileBase64;
    private String description;
    private Set<String> tags;
}
