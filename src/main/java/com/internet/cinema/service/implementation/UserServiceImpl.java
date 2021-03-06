package com.internet.cinema.service.implementation;

import com.internet.cinema.dao.UserDao;
import com.internet.cinema.model.User;
import com.internet.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElseThrow();
    }
}
