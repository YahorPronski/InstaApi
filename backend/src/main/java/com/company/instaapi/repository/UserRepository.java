package com.company.instaapi.repository;

import com.company.instaapi.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
