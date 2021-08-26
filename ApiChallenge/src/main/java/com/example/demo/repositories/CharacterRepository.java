package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character,Integer> {

}
