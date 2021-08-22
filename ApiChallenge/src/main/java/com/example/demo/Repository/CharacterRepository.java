package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Models.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character,Integer> {

}
