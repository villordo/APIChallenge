package com.example.demo.services;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.models.Character;
import com.example.demo.models.Movie;
import com.example.demo.repositories.MovieRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Integer idMovie) {
        return movieRepository.getById(idMovie);
    }

    public Movie getMovieByTitle(String title) throws NotFoundException {
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Movie doesn't exists."));
    }

    public List<Movie> getMoviesByGenre(Integer genre) {
        return movieRepository.getMoviesByIdGender(genre);
    }

    public List<Movie> getMoviesByOrder(String order) {
        List<Movie> movies = (order.equals("ASC"))?
                movieRepository.findAllByOrderByDateAsc() :
                movieRepository.findAllByOrderByDateDesc();
        return movies;
    }

    public Movie addMovie(Movie movie) throws AlreadyExistsException {
        if(movieRepository.findByTitle(movie.getTitle()).isPresent()){
            throw new AlreadyExistsException("Movie already exists.");
        }
        return movieRepository.save(movie);
    }

    public void removeMovie(Integer idMovie) throws NotFoundException {
        Movie toRemove = movieRepository.findById(idMovie)
                .orElseThrow(() -> new NotFoundException("Movie doesn't exist."));
        movieRepository.delete(toRemove);
    }

    public void updateMovie(Movie updatedMovie) throws NotFoundException {
        Movie toUpdate = movieRepository.findById(updatedMovie.getId())
                .orElseThrow(() -> new NotFoundException("Movie doesn't exist."));
        movieRepository.save(updatedMovie);
    }
}
