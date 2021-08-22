package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table ( name = "Gender" )
public class Gender {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)//Autoincrement
    private Integer id;
    private String name;
    private Boolean image;

    //private List<Movie> movieList = new ArrayList<Movie>();

}
