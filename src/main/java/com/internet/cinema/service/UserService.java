package com.internet.cinema.service;

import com.internet.cinema.model.User;
import java.util.Optional;

public interface UserService {

    User add(User user);

    Optional<User> findByEmail(String email);
}
