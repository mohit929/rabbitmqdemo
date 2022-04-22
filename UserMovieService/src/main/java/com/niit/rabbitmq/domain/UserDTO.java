package com.niit.rabbitmq.domain;

import com.niit.usermovieservice.domain.Movie;

import java.util.List;

public class UserDTO {
    private String email;
    private String password;

    public UserDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
