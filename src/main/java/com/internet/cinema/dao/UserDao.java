package com.internet.cinema.dao;

import com.internet.cinema.model.User;
import java.util.Optional;

public interface UserDao {

    User add(User user);

    User get(Long id);

    Optional<User> findByEmail(String email);
}
