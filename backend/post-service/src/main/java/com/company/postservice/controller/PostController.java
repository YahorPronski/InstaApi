package com.company.postservice.controller;

import com.company.postservice.dto.PostRequest;
import com.company.postservice.dto.PostResponse;
import com.company.postservice.model.Post;
import com.company.postservice.model.Tag;
import com.company.postservice.service.PostService;
import com.company.postservice.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileUtil fileUtil;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePost(@RequestBody @Valid PostRequest postRequest,
                         @RequestHeader("X-auth-user-id") Long userId) {
        byte[] file = fileUtil.decodeBase64(postRequest.getFileBase64());
        Post post = mapToPost(postRequest);
        post.setUserId(userId);
        postService.savePost(post, file);
    }

    @GetMapping
    public List<PostResponse> getUserPosts(@RequestParam String userId) {
        if (!NumberUtils.isParsable(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user id");
        }
        return postService.getPostsByUserId(Long.parseLong(userId));
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
