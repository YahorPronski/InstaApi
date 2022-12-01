package com.company.userservice.controller;

import com.company.userservice.dto.CredentialsDto;
import com.company.userservice.dto.UserRequest;
import com.company.userservice.dto.UserResponse;
import com.company.userservice.model.User;
import com.company.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody @Valid UserRequest userRequest) {
        if (userService.isEmailExists(userRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account with this email already exists");
        }

        if (userService.isUsernameExists(userRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account with this username already exists");
        }

        User user = modelMapper.map(userRequest, User.class);
        userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        return userService
                .getUserById(id)
                .map(this::mapUserToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user id"));
    }

    @PostMapping("/id")
    public Long getUserIdByCredentials(@RequestBody @Valid CredentialsDto credentialsDto) {
        return userService
                .getUserByCredentials(credentialsDto.getUsername(), credentialsDto.getPassword())
                .map(User::getId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));
    }

    private UserResponse mapUserToResponse(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .postsCount(user.getPostIds().size())
                .followersCount(user.getFollowerIds().size())
                .followingCount(user.getFollowingIds().size())
                .build();
    }

}
