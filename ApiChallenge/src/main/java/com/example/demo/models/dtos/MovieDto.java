package com.example.demo.models.dtos;

import com.example.demo.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {

    private String image;
    private String title;
    private Date dateCreation;

    public MovieDto(Movie movie) {
        this.image = movie.getImage();
        this.title = movie.getTitle();
        this.dateCreation = movie.getDate();
    }
}
