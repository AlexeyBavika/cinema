package com.internet.cinema.controller;

import com.internet.cinema.exception.AuthenticationException;
import com.internet.cinema.model.dto.UserRequestDto;
import com.internet.cinema.model.dto.UserResponseDto;
import com.internet.cinema.security.AuthenticationService;
import com.internet.cinema.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRequestDto userRequestDto)
            throws AuthenticationException {
        return userMapper.getUserResponseDtoFromUser(authenticationService
        .register(userRequestDto.getEmail(), userRequestDto.getPassword()));
    }
}
