package com.internet.cinema.service;

import com.internet.cinema.model.User;

public interface UserService {

    User add(User user);

    User findByEmail(String email);
}
