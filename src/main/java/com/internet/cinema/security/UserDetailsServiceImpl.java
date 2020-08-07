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
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email
                    + " not found");
        }
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User
                .withUsername(email);
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRoles().stream()
                .map(role -> role.getRoleName().name())
                .toArray(String[]::new));
        return userBuilder.build();
    }
}
