package com.practice.splitexpense.controller;

import com.practice.splitexpense.dtos.CreateUserDto;
import com.practice.splitexpense.models.User;
import com.practice.splitexpense.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    // Transformation
    public User createUser(CreateUserDto request) {
        return userService.createUser(request.toUser());
    }
}
