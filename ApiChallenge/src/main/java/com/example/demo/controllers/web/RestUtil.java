package com.example.demo.controllers.web;

import com.example.demo.models.Character;
import com.example.demo.models.User;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class RestUtil {

    public static URI getLocationCharacter(Character character) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id_character}")
                .buildAndExpand(character.getIdCharacter())
                .toUri();
    }
    public static URI getLocationUser(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id_user}")
                .buildAndExpand(user.getId_user())
                .toUri();
    }
}
