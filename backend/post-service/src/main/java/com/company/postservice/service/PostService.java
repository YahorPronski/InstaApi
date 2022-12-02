package com.company.postservice.service;

import com.company.postservice.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post savePost(Post post, byte[] file);
    Optional<Post> getPostById(String postId);
    void deletePostById(String postId);
    List<Post> getPostsByUserId(String userId);
}
