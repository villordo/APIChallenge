package com.example.demo.controllers;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotValidRolException;
import com.example.demo.models.User;
import com.example.demo.models.dtos.PostUserDto;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User save(@RequestBody PostUserDto user) throws NotValidRolException, AlreadyExistsException {

        return userService.save(user);
    }
}
