package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO) //Autoincrement
    private Integer movie_id;
    private Boolean image;
    private String title;
    private Date date;
    private Integer qualification;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id", referencedColumnName = "gender_id")
    //@JsonBackReference(value = "phoneLineUser") TODO: Buscar pa q sirve
    private Gender gender;
    @ManyToMany ( mappedBy = "movieList")
    private List<Character> characterList = new ArrayList<Character>();

}
