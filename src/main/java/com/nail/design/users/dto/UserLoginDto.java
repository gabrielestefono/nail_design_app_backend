package com.nail.design.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserLoginDto {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    public UserLoginDto() {
        // Empty Constructor
    }

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
}
