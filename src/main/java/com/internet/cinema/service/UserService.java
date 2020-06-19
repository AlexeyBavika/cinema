package com.internet.cinema.service;

import com.internet.cinema.model.User;

public interface UserService {

    User add(User user);

    User get(Long id);

    User findByEmail(String email);
}
