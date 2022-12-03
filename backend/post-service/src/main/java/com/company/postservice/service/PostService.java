package com.company.postservice.service;

import com.company.postservice.dto.PostResponse;
import com.company.postservice.model.Post;

import java.util.List;

public interface PostService {
    Post savePost(Post post, byte[] file);
    List<PostResponse> getPostsByUserId(Long userId);
}
