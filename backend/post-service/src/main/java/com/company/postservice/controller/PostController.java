package com.company.postservice.controller;

import com.company.postservice.dto.PostRequest;
import com.company.postservice.dto.PostResponse;
import com.company.postservice.model.Post;
import com.company.postservice.model.Tag;
import com.company.postservice.service.PostService;
import com.company.postservice.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @GetMapping("/{post-id}")
    public PostResponse getPost(@PathVariable("post-id") String postId) {
        return postService.getPostById(postId)
                .map(post -> modelMapper.map(post, PostResponse.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePost(@RequestBody @Valid PostRequest postRequest,
                         @RequestHeader("X-auth-user-id") long userId) {
        byte[] file = fileUtil.convertFromBase64(postRequest.getFileBase64());
        Post post = mapToPost(postRequest);
        post.setUserId(userId);
        postService.savePost(post, file);
    }

    @DeleteMapping("/{post-id}")
    public void deletePost(@PathVariable("post-id") String postId) {
        postService.deletePostById(postId);
    }

    private Post mapToPost(PostRequest postRequest) {
        return Post.builder()
                .description(postRequest.getDescription())
                .tags(postRequest.getTags() != null ?
                        postRequest.getTags().stream()
                        .map(tag -> Tag.builder().name(tag).build())
                        .collect(Collectors.toSet()) : new HashSet<>())
                .build();
    }

}
