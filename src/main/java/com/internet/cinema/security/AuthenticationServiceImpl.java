package com.internet.cinema.security;

import com.internet.cinema.exception.AuthenticationException;
import com.internet.cinema.lib.Inject;
import com.internet.cinema.lib.Service;
import com.internet.cinema.model.User;
import com.internet.cinema.service.ShoppingCartService;
import com.internet.cinema.service.UserService;
import com.internet.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email).get();
        if (user.getPassword().equals(HashUtil.hashPassword(user.getPassword(),
                user.getSalt()))) {
            return user;
        } else {
            throw new AuthenticationException("Incorrect email or password");
        }
    }

    @Override
    public User register(String email, String password) throws AuthenticationException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
