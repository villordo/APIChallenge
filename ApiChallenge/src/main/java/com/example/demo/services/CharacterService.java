package com.example.demo.services;


import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.models.Character;
import com.example.demo.models.dtos.CharacterDto;
import com.example.demo.repositories.CharacterRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    public Character addCharacter(Character character) throws AlreadyExistsException {
        /*if(characterRepository.findById(character.getCharacter_id()).isPresent()){
            throw new AlreadyExistsException();  //Que campo NO se deberia repetir?
        }*/
        return characterRepository.save(character);
    }

    public Character getCharacterById(Integer characterId) throws NotFoundException {
        return characterRepository.findById(characterId)
                                  .orElseThrow(() -> new NotFoundException("Character doesn't exist."));
    }

    public List<Character> getAll() {
        return characterRepository.findAll();
    }

    public void remove(Integer characterId) throws NotFoundException {

        Character toRemove = characterRepository.findById(characterId).orElseThrow(() -> new NotFoundException("Character doesn't exist."));
        characterRepository.delete(toRemove);
    }
    //TODO:probar esta funcion
    public Character updateCharacter(Character updatedCharacter) throws NotFoundException {

        Character oldCharacter = removedVerification(updatedCharacter.getIdCharacter());
        Optional.ofNullable(updatedCharacter.getName()).ifPresent(oldCharacter::setName);
        return characterRepository.save(oldCharacter);
    }

    private Character removedVerification(Integer idCharacter) throws NotFoundException {

        return characterRepository.findById(idCharacter)
                .orElseThrow(() -> new NotFoundException("User doesn't exists."));
    }

    public Character getCharacterByName(String name) throws NotFoundException {
        return characterRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Character doesn't exist."));
    }

    public List<Character> getCharacterByAge(Integer age) throws NotFoundException {
        return characterRepository.findAllByAge(age);

    }

    public List<Character> getCharactersByWeight(Integer weight) {
        return characterRepository.findAllByWeight(weight);
    }
}
