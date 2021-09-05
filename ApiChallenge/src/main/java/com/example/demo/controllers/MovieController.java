package com.example.demo.controllers;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.models.Character;
import com.example.demo.models.Movie;
import com.example.demo.services.MovieService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public List<Movie> getAll() {
        return movieService.getAll();
    }

    public Movie getMoviesById(Integer idMovie) {
        return movieService.getMovieById(idMovie);
    }

    public Movie getMovieByTitle(String name) throws NotFoundException {
        return movieService.getMovieByTitle(name);
    }

    public List<Movie> getMoviesByGenre(Integer genre) {
        return movieService.getMoviesByGenre(genre);
    }

    public List<Movie> getMoviesByOrder(String order) {
        return movieService.getMoviesByOrder(order);
    }

    public Movie addMovie(Movie movie) throws AlreadyExistsException {
        return movieService.addMovie(movie);
    }

    public void removeMovie(Integer idMovie) throws NotFoundException {
        movieService.removeMovie(idMovie);
    }

    public void updateMovie(Movie updatedMovie) throws NotFoundException {
        movieService.updateMovie(updatedMovie);
    }
}
