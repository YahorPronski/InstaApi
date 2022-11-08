package com.company.instaapi.service;

import com.company.instaapi.domain.Post;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Post savePost(@NotNull Post post);
    Optional<Post> getPostById(@NotNull String postId);
    void deletePostById(@NotNull String postId);
    List<Post> getPostsByUserId(@NotNull String userId);
}
