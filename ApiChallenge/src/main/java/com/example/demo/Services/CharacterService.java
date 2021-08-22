package com.example.demo.Services;


import com.example.demo.Exceptions.AlreadyExistsException;
import com.example.demo.Models.Character;
import com.example.demo.Repository.CharacterRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    public void addCharacter(Character character) throws AlreadyExistsException {
        if(characterRepository.findById(character.getId()).isPresent()){
            throw new AlreadyExistsException();
        }
        characterRepository.save(character);
    }

    public Character getCharacterById(Integer characterId) throws NotFoundException {
        return characterRepository.findById(characterId)
                                  .orElseThrow(() -> new NotFoundException("Character doesn't exist."));
    }

    public List<Character> getAll() {
        return characterRepository.findAll();
    }
}
