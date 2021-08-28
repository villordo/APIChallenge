package com.example.demo.controllers;

import com.example.demo.exceptions.NotValidRolException;
import com.example.demo.models.User;
import com.example.demo.models.dtos.PostUsersDto;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public User save(@RequestBody PostUsersDto user) throws NotValidRolException {

        return userService.save(user);
    }
}
