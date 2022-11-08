package com.company.instaapi.filter;

import com.company.instaapi.domain.user.User;
import com.company.instaapi.service.UserService;
import com.company.instaapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtFilter extends GenericFilterBean {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Lazy
    @Autowired
    public JwtFilter(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String authHeader = ((HttpServletRequest) servletRequest).getHeader(AUTHORIZATION_HEADER);
        String jwtToken = jwtUtil.getTokenFromAuthHeader(authHeader);
        String userId = jwtUtil.getUserIdFromToken(jwtToken);

        if (userId != null) {
            Optional<User> userOpt = userService.findUserById(userId);

            if (userOpt.isPresent()) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userOpt.get(), null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
