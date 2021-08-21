package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Gender {
    private String name;
    private Boolean image;
    private List<Movie> movieList = new ArrayList<Movie>();

}
