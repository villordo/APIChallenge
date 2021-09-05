package com.example.demo.controllers;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.models.dtos.CharacterDto;
import com.example.demo.models.dtos.response.CharDetailResponseDto;
import com.example.demo.models.proyections.CharactersByMovie;
import com.example.demo.services.CharacterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Character;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService){
        this.characterService = characterService;
    }


    public Character getCharacterById(@PathVariable Integer characterId) throws NotFoundException {
        return characterService.getCharacterById(characterId);
    }

    public List<Character> getAll(){
        return characterService.getAll();
    }

    public Character addCharacter(@RequestBody Character character) throws AlreadyExistsException {
       return characterService.addCharacter(character);
    }

    public void removeCharacter(@PathVariable Integer characterId) throws NotFoundException {
        characterService.remove(characterId);
    }

    public void updateCharacter(Character updatedCharacter) throws NotFoundException {
        characterService.updateCharacter(updatedCharacter);
    }

    public Character getCharacterByName(String name) throws NotFoundException {
       return characterService.getCharacterByName(name);
    }

    public List<Character> getCharactersByAge(Integer age) throws NotFoundException {
        return characterService.getCharacterByAge(age);
    }

    public List<Character> getCharactersByWeight(Integer weight) {
        return characterService.getCharactersByWeight(weight);
    }

    public List<CharactersByMovie> getCharactersByIdMovie(Integer idMovie) {
        return characterService.getCharactersByIdMovie(idMovie);
    }
}
