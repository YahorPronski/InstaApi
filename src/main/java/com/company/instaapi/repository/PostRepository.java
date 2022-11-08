package com.company.instaapi.repository;

import com.company.instaapi.domain.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, String> {

    @Query("select p from Post p where p.user.id = ?1")
    List<Post> getPostsByUserId(String userId);

}
