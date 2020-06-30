package com.internet.cinema.controller;

import com.internet.cinema.model.dto.UserResponseDto;
import com.internet.cinema.service.UserService;
import com.internet.cinema.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/by-email")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        return userMapper.getUserResponseDtoFromUser(userService
                .findByEmail(email));
    }
}
