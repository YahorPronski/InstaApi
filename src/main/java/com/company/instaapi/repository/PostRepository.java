package com.company.instaapi.repository;

import com.company.instaapi.domain.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends CrudRepository<Post, UUID> {

    @Query("select p from Post p where p.user.id = ?1")
    List<Post> getPostsByUserId(String userId);

}
