package com.example.demo.repositories;

import com.example.demo.models.User;
import com.example.demo.models.dtos.CharacterDto;
import com.example.demo.models.dtos.response.CharDetailResponseDto;
import com.example.demo.models.proyections.CharactersByMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select c.image image, c.name name\n" +
            "from movies as m\n" +
            "join characters_x_movies as cm\n" +
            "on m.id_movie = cm.id_movie\n" +
            "join characters as c\n" +
            "on cm.id_character = c.id_character\n" +
            "where cm.id_movie = ?1 ;"
            , nativeQuery = true)
    List<CharactersByMovie> getCharactersByIdMovie(Integer idMovie);
}
