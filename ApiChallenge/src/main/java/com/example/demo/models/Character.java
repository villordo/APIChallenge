package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //Le decimos que es una entidad de persistencia
@Table( name = "Character")
public class Character {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer character_id;

    private Boolean image;
    private String name;
    private Integer age;
    private Integer weight;
    private String history;
    @ManyToMany ()
    @JoinTable(
            name = "characters_x_movies",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movieList = new ArrayList<Movie>();

}
