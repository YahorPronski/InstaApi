package com.company.instaapi.controller;

import com.company.instaapi.domain.Post;
import com.company.instaapi.domain.user.User;
import com.company.instaapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> getAllPostsByUserId(@RequestParam String userId) {
        return postService.getPostsByUserId(userId);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable String postId) {
        return postService.getPostById(postId).orElse(null);
    }

    @PostMapping
    public HttpStatus savePost(@RequestBody @Valid Post post,
                               BindingResult bindingResult,
                               Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return HttpStatus.UNAUTHORIZED;
        }

        if (bindingResult.hasErrors()) {
            return HttpStatus.BAD_REQUEST;
        }

        User postOwner = new User();
        postOwner.setId(getUserId(authentication));
        post.setUser(postOwner);

        postService.savePost(post);
        return HttpStatus.OK;

    }

    @DeleteMapping("/{postId}")
    public HttpStatus deletePostById(@PathVariable String postId, Authentication authentication) {
        Optional<Post> postOpt = postService.getPostById(postId);
        if (!postOpt.isPresent()) {
            return HttpStatus.NOT_FOUND;
        }

        if (authentication == null || !authentication.isAuthenticated() ||
                !postOpt.get().getUser().getId().equals(getUserId(authentication))) {
            return HttpStatus.UNAUTHORIZED;
        }

        postService.deletePostById(postId);
        return HttpStatus.OK;
    }

    private String getUserId(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getId();
    }

}
