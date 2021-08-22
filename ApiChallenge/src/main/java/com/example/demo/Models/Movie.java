package com.example.demo.Models;

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
    private Integer id;
    private Boolean image;
    private String title;
    private Date date;
    private Integer qualification;
    @ManyToMany ( mappedBy = "movieList")
    private List<Character> characterList = new ArrayList<Character>();

}
