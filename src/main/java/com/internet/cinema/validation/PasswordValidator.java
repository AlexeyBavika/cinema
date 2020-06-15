package com.internet.cinema.validation;

import com.internet.cinema.model.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidatePassword, UserRequestDto> {
    @Override
    public boolean isValid(UserRequestDto userRequestDto, ConstraintValidatorContext
            constraintValidatorContext) {
        return userRequestDto.getPassword().equals(userRequestDto.getRepeatPassword());
    }
}
