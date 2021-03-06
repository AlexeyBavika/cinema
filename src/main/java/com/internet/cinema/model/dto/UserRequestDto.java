package com.internet.cinema.model.dto;

import com.internet.cinema.validation.ValidateEmail;
import com.internet.cinema.validation.ValidatePassword;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ValidatePassword
public class UserRequestDto {
    @NotNull
    @ValidateEmail
    private String email;
    @NotNull
    @Size(min = 3, message = "Password should contains 3 or more symbols")
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
