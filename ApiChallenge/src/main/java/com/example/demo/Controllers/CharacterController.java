package com.example.demo.Controllers;

import com.example.demo.Models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Models.Character;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/character")
public class CharacterController {

    @GetMapping("/")
    public Character getCharacter(){
        Character char1 = new Character(true,"Alex Plosivo",26,88,"Boom",null);
        ArrayList<Character> characters = new ArrayList<Character>();
        characters.add(char1);
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        Movie movie = new Movie(true,"Titanic", null,5,characters);
        movieList.add(movie);
        return new Character(true,"Georgie",26,76,"Un pibe buena gente.",movieList);
    }
}
