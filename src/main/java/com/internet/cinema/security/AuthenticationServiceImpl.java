package com.internet.cinema.security;

import com.internet.cinema.exception.AuthenticationException;
import com.internet.cinema.lib.Inject;
import com.internet.cinema.lib.Service;
import com.internet.cinema.model.User;
import com.internet.cinema.service.UserService;
import com.internet.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(HashUtil.hashPassword(user.getPassword(),
                    user.getSalt()))) {
                return user;
            } else {
                throw new AuthenticationException("Incorrect password");
            }
        } else {
            throw new AuthenticationException("Incorrect email");
        }
    }

    @Override
    public User register(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setPassword(password);
            userService.add(user);
            return user;
        } else {
            throw new AuthenticationException("Email " + email + " is already exists.");
        }
    }
}
