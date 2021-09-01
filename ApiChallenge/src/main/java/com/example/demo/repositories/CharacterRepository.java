package com.example.demo.repositories;

import com.example.demo.models.User;
import com.example.demo.models.dtos.CharacterDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Character;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character,Integer> {
    Optional<Character> findByName(String name);
    List<Character> findAllByAge(Integer age);
    List<Character> findAllByWeight(Integer weight);
}
