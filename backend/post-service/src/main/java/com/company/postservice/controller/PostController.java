package com.company.postservice.controller;

import com.company.postservice.dto.PostRequest;
import com.company.postservice.dto.PostResponse;
import com.company.postservice.model.Post;
import com.company.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final ModelMapper modelMapper;

    @GetMapping("/{post-id}")
    public PostResponse getPost(@PathVariable("post-id") String postId) {
        return postService.getPostById(postId)
                .map(post -> modelMapper.map(post, PostResponse.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePost(@RequestPart @Valid PostRequest postRequest,
                         @RequestPart @NotNull MultipartFile file) {
        Post post = modelMapper.map(postRequest, Post.class);
        postService.savePost(post, file);
    }

    @DeleteMapping("/{post-id}")
    public void deletePost(@PathVariable("post-id") String postId) {
        postService.deletePostById(postId);
    }

}
