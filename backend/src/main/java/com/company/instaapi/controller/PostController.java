package com.company.instaapi.controller;

import com.company.instaapi.domain.Post;
import com.company.instaapi.domain.user.User;
import com.company.instaapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<String> savePost(@RequestBody @Valid Post post, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>("User is not authorized", HttpStatus.UNAUTHORIZED);
        }

        User postOwner = new User();
        postOwner.setId(getUserId(authentication));
        post.setUser(postOwner);

        postService.savePost(post);
        return new ResponseEntity<>("Post successfully saved", HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable String postId, Authentication authentication) {
        Optional<Post> postOpt = postService.getPostById(postId);
        if (!postOpt.isPresent()) {
            return new ResponseEntity<>("Post wasn't found by the id=" + postId, HttpStatus.NOT_FOUND);
        }

        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>("User is not authorized", HttpStatus.UNAUTHORIZED);
        }

        if (!postOpt.get().getUser().getId().equals(getUserId(authentication))) {
            return new ResponseEntity<>("User cannot delete other people's posts", HttpStatus.FORBIDDEN);
        }

        postService.deletePostById(postId);
        return new ResponseEntity<>("Post successfully deleted", HttpStatus.OK);
    }

    private String getUserId(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getId();
    }

}
