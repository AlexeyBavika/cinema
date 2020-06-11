package com.internet.cinema.util.mapper;

import com.internet.cinema.model.User;
import com.internet.cinema.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto getUserResponseDtoFromUser(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
