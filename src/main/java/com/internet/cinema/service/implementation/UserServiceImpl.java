package com.internet.cinema.service.implementation;

import com.internet.cinema.dao.UserDao;
import com.internet.cinema.lib.Inject;
import com.internet.cinema.lib.Service;
import com.internet.cinema.model.User;
import com.internet.cinema.service.UserService;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
