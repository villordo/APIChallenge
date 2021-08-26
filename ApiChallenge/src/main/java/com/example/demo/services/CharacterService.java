package com.example.demo.services;


import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.models.Character;
import com.example.demo.repositories.CharacterRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    public void addCharacter(Character character) throws AlreadyExistsException {
        /*if(characterRepository.findById(character.getCharacter_id()).isPresent()){
            throw new AlreadyExistsException();  //Que campo NO se deberia repetir?
        }*/
        characterRepository.save(character);
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
}
