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
@Table( name = "characters")
public class Character {

    @Id
    @Column(name = "id_character")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_character;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private Boolean image;

    @Column(name = "age")
    private Integer age;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "history")
    private String history;

    @ManyToMany ()
    @JoinTable(
            name = "characters_x_movies",
            joinColumns = @JoinColumn(name = "id_character"),
            inverseJoinColumns = @JoinColumn(name = "id_movie")
    )
    private List<Movie> movieList = new ArrayList<Movie>();

}
