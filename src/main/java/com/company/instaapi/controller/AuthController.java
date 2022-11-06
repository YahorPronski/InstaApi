package com.company.instaapi.controller;

import com.company.instaapi.domain.user.User;
import com.company.instaapi.domain.user.UserAuthInfo;
import com.company.instaapi.service.UserService;
import com.company.instaapi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody @Valid UserAuthInfo userAuthInfo,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not all required fields are filled");
        }

        return userService.findUserByAuthInfo(userAuthInfo)
                .map(user -> JwtUtil.generateToken(user.getAuthInfo().getUsername()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));
    }

    @PostMapping("/register")
    public HttpStatus register(@RequestBody @Valid User user,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpStatus.BAD_REQUEST;
        }

        userService.saveUser(user);
        return HttpStatus.OK;
    }

}
