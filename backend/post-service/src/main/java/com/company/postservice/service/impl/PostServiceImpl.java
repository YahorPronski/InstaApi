package com.company.postservice.service.impl;

import com.company.postservice.dto.PostResponse;
import com.company.postservice.model.Post;
import com.company.postservice.model.Tag;
import com.company.postservice.repository.PostRepository;
import com.company.postservice.service.PostService;
import com.company.postservice.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<PostResponse> getPostsByUserId(Long userId) {
        return postRepository.getPostsByUserId(userId).stream()
                .map(post -> {
                    byte[] file = fileUtil.readFile(userId + "/" + post.getId());
                    return mapToPostResponse(post, fileUtil.encodeBase64(file));
                }).collect(Collectors.toList());
    }

    private PostResponse mapToPostResponse(Post post, String fileBase64) {
        return PostResponse.builder()
                .id(post.getId())
                .fileBase64(fileBase64)
                .description(post.getDescription())
                .creationDate(post.getCreationDate().toString())
                .likes(post.getLikedUserIds().size())
                .tags(post.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toSet()))
                .build();
    }

}
