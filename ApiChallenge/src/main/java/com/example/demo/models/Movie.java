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
@Table(name = "movies")
public class Movie {

    @Id
    @Column(name = "id_movie")
    @GeneratedValue (strategy = GenerationType.IDENTITY) //Autoincrement
    private Integer movie_id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "creation_date")
    private Date date;

    @Column(name = "qualification")
    private Integer qualification;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gender", referencedColumnName = "id_gender")
    //@JsonBackReference(value = "phoneLineUser") TODO: Buscar pa q sirve
    private Gender gender;

    @ManyToMany ( mappedBy = "movieList")
    private List<Character> characterList = new ArrayList<Character>();

}
