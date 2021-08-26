package com.example.demo.controllers;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.services.CharacterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Character;

import java.util.List;

@RestController
@RequestMapping("/character")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService){
        this.characterService = characterService;
    }
    /*
       Obtiene un Character especifico segun el ID
    */
    @GetMapping("/{characterId}")
    public Character getCharacter(@PathVariable Integer characterId) throws NotFoundException {
        return characterService.getCharacterById(characterId);
    }
    /*
       Obtiene todos los registros de la tabla characters
    */
    @GetMapping("/")
    public List<Character> getAll(){
        return characterService.getAll();
    }
    /*
       Agrega un nuevo Character a la BDD
    */
    @PostMapping("/")       //@RequestBody convierte la peticion web(Json) al obj Character
    public void addCharacter(@RequestBody Character character) throws AlreadyExistsException {
        characterService.addCharacter(character);
    }

    /*
        Borra un registro que coincida con el campo character_id
    */
    @DeleteMapping("/{characterId}")
    public void removeCharacter(@PathVariable Integer characterId) throws NotFoundException {
        characterService.remove(characterId);
    }
}
