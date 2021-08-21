package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

    private Boolean image;
    private String title;
    private Date date;
    private Integer qualification;
    private List<Character> characterList = new ArrayList<Character>();

}
