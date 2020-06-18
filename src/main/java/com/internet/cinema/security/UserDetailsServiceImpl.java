package com.internet.cinema.security;

import com.internet.cinema.model.User;
import com.internet.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserBuilder userBuilder;
        try {
            User user = userService.findByEmail(email).orElseThrow();
            userBuilder = org.springframework.security.core.userdetails.User
                    .withUsername(email);
            userBuilder.password(user.getPassword());
            userBuilder.roles(user.getRoles().stream()
                    .map(role -> role.getRoleName().name())
                    .toArray(String[]::new));
            return userBuilder.build();
        } catch (Exception e) {
            throw new UsernameNotFoundException("No user found");
        }
    }
}
