package com.example.demo.controllers.web;

import com.example.demo.models.Character;
import com.example.demo.models.Movie;
import com.example.demo.models.User;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class RestUtil {

    public static URI getLocationCharacter(Character character) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(character.getId())
                .toUri();
    }
    public static URI getLocationUser(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
    }
    public static URI getLocationMovie(Movie movie) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(movie.getId())
                .toUri();
    }
}
