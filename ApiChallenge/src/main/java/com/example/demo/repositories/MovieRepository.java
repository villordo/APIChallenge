package com.example.demo.repositories;

import com.example.demo.models.Movie;
import com.example.demo.models.proyections.CharactersByMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findByTitle(String title);

    List<Movie> findAllByOrderByDateAsc();

    List<Movie> findAllByOrderByDateDesc();


    @Query(value = "select * from movies\n" +
            "where id_gender = ?1 ;"
            , nativeQuery = true)
    List<Movie> getMoviesByIdGender(Integer idGender);
}
