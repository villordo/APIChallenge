package com.example.demo.controllers.web;

import com.example.demo.controllers.UserController;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotValidRolException;
import com.example.demo.models.dtos.PostUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class UserWebController {

    private final UserController userController;

    @Autowired
    public UserWebController(UserController userController) {
        this.userController = userController;
    }

    @PostMapping()
    public ResponseEntity registerUser(@RequestBody PostUserDto newUser) throws AlreadyExistsException, NotValidRolException {
        return ResponseEntity.created(RestUtil.getLocationUser(userController.save(newUser))).build();
    }
}
