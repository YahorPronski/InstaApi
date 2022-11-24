package com.company.postservice.service.impl;

import com.company.postservice.model.Post;
import com.company.postservice.repository.PostRepository;
import com.company.postservice.service.PostService;
import com.company.postservice.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final FileUploadUtil  fileUploadUtil;

    @Override
    public Post savePost(@NotNull Post post, @NotNull MultipartFile file) {
        String fileName = UUID.randomUUID().toString();
        fileUploadUtil.saveFile("userId", fileName, file);
        post.setImageSrc("userId" + fileName);
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> getPostById(@NotNull String postId) {
        return postRepository.findById(postId);
    }

    @Override
    public void deletePostById(@NotNull String postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> getPostsByUserId(@NotNull String userId) {
        return postRepository.getPostsByUserId(userId);
    }

}
