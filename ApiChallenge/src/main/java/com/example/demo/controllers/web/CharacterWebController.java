package com.example.demo.controllers.web;

import com.example.demo.controllers.CharacterController;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.models.Character;
import com.example.demo.models.dtos.CharacterDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/characters")
public class CharacterWebController {

    private final CharacterController characterController;

    @Autowired
    public CharacterWebController(CharacterController characterController) {
        this.characterController = characterController;
    }

    /*************************************************************************************************************************************/

    /*
       Obtiene un Character especifico segun el ID
    */
    @GetMapping("/{characterId}")
    public ResponseEntity getCharacterById(@PathVariable Integer characterId) throws NotFoundException {
        Character character = characterController.getCharacterById(characterId);
        return (character != null)? ResponseEntity.ok(character) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(character);
    }
    /*
        Obtiene un Character especifico segun el nombre
     */
    @RequestMapping(params="name", method = RequestMethod.GET)
    public ResponseEntity getCharacterByName(@RequestParam(value = "name") String name) throws NotFoundException {
        Character character = characterController.getCharacterByName(name);
        return (character != null)? ResponseEntity.ok(character) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(character);
    }
    /*
        Obtiene una lista de Characters de una edad especifica
     */
    @RequestMapping(params="age", method = RequestMethod.GET)
    public ResponseEntity<List<CharacterDto>> getCharactersByAge(@RequestParam(value = "age") Integer age) throws NotFoundException {
        List<CharacterDto> characters = characterController.getCharactersByAge(age)
                .stream()
                .map(CharacterDto::new)
                .collect(Collectors.toList());
        return characters.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(characters) : ResponseEntity.ok(characters);
    }
    /*
       Obtiene una lista de Characters de un peso especifico
    */
    @RequestMapping(params="weight", method = RequestMethod.GET)
    public ResponseEntity<List<CharacterDto>> getCharactersByWeight(@RequestParam(value = "weight") Integer weight) throws NotFoundException {
        List<CharacterDto> characters = characterController.getCharactersByWeight(weight)
                .stream()
                .map(CharacterDto::new)
                .collect(Collectors.toList());
        return characters.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(characters) : ResponseEntity.ok(characters);
    }
    /*
       Obtiene todos los registros de la tabla characters
    */
    @GetMapping("/")
    public ResponseEntity<List<CharacterDto>> getAllCharacters(){
        List<CharacterDto> characters = characterController.getAll()
                .stream()
                .map(CharacterDto::new)
                .collect(Collectors.toList());
        return characters.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(characters) : ResponseEntity.ok(characters);
    }
/*************************************************************************************************************************************/
    /*
       Agrega un nuevo Character a la BDD
    */
    @PostMapping("/")       //@RequestBody convierte la peticion web(Json) al obj Character
    public ResponseEntity addCharacter(@RequestBody Character character) throws AlreadyExistsException {
        return ResponseEntity.created(RestUtil.getLocationCharacter(characterController.addCharacter(character))).build();
    }

    /*
        Borra un registro que coincida con el campo character_id
    */
    @DeleteMapping("/{characterId}")
    public ResponseEntity deleteCharacter(@PathVariable Integer idCharacter) throws NotFoundException {
        characterController.removeCharacter(idCharacter);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/")
        public ResponseEntity updateCharacter(@RequestBody Character updatedCharacter) throws NotFoundException {
        characterController.updateCharacter(updatedCharacter);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
