package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Character {
    private Boolean image;
    private String name;
    private Integer age;
    private Integer weight;
    private String history;
    private List<Movie> movieList = new ArrayList<Movie>();



}
