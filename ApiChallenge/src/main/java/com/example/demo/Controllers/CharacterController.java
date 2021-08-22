package com.example.demo.Controllers;

import com.example.demo.Exceptions.AlreadyExistsException;
import com.example.demo.Services.CharacterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Models.Character;

import java.util.List;
import java.util.Optional;

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
       Agrega un nuevo Character a la BDD
    */
    @PostMapping("/")       //@RequestBody convierte la peticion web(Json) al obj Character
    public void addCharacter(@RequestBody Character character) throws AlreadyExistsException {
        characterService.addCharacter(character);
    }
    /*
        Obtiene todos los registros de la tabla characters
    */
    @GetMapping("/")
    public List<Character> getAll(){
        return characterService.getAll();
    }
}
