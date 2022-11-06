package com.company.instaapi.repository;

import com.company.instaapi.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u where u.authInfo.username = ?1")
    User findUserByUsername(String email);

}
