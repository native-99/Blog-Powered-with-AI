package com.excersice.blog.service;

import com.excersice.blog.properties.SecretProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SecretProperties secretProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || !username.equals(secretProperties.getUserUsername())) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username(secretProperties.getUserUsername())
                .password(secretProperties.getUserPassword())
                .build();
    }
}