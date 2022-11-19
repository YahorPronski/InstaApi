package com.company.postservice.repository;

import com.company.postservice.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, String> {

    @Query("select p from Post p where p.userId = ?1")
    List<Post> getPostsByUserId(String userId);

}
