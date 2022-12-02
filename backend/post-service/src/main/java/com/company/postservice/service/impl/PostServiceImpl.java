package com.company.postservice.service.impl;

import com.company.postservice.model.Post;
import com.company.postservice.repository.PostRepository;
import com.company.postservice.service.PostService;
import com.company.postservice.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final FileUtil fileUtil;

    @Override
    public Post savePost(Post post, byte[] file) {
        fileUtil.saveFile(post.getUserId().toString(), post.getId(), file);
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> getPostById(String postId) {
        return postRepository.findById(postId);
    }

    @Override
    public void deletePostById(String postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> getPostsByUserId(String userId) {
        return postRepository.getPostsByUserId(userId);
    }

}
