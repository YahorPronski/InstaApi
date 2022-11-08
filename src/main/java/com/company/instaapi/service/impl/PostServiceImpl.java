package com.company.instaapi.service.impl;

import com.company.instaapi.domain.Post;
import com.company.instaapi.repository.PostRepository;
import com.company.instaapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post savePost(@NotNull Post post) {
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> getPostById(@NotNull String id) {
        return postRepository.findById(UUID.fromString(id));
    }

    @Override
    public void deletePostById(@NotNull String id) {
        postRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<Post> getPostsByUserId(@NotNull String userId) {
        return postRepository.getPostsByUserId(userId);
    }
}
