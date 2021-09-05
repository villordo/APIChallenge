package com.example.demo.controllers.web;

import com.example.demo.controllers.CharacterController;
import com.example.demo.controllers.MovieController;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.models.Character;
import com.example.demo.models.Movie;
import com.example.demo.models.dtos.CharacterDto;
import com.example.demo.models.dtos.MovieDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieWebController {

    private final MovieController movieController;

    @Autowired
    public MovieWebController(MovieController movieController) {
        this.movieController = movieController;
    }
    //*****************************---------------------------GET---------------------------*******************************************************************************/
    /*
       Obtiene todos los registros de la tabla movies
    */
    @GetMapping("/")
    public ResponseEntity<List<MovieDto>> getAllCharacters(){
        List<MovieDto> characters = movieController.getAll()
                .stream()
                .map(MovieDto::new)
                .collect(Collectors.toList());
        return characters.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(characters) : ResponseEntity.ok(characters);
    }
    /*
       Obtiene una Movie especifica segun el ID
    */
    @GetMapping("/{idMovie}")
    public ResponseEntity getMovieById(@PathVariable Integer idMovie) throws NotFoundException {
        //CharDetailResponseDto character = CharDetailResponseDto.buildResponse(characterController.getCharacterById(characterId));
        Movie movie = movieController.getMoviesById(idMovie);

        return (movie != null)? ResponseEntity.ok(movie) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(movie);
    }
    /*
       Obtiene una Movie especifica segun el nombre
    */
    @RequestMapping(params="name", method = RequestMethod.GET)
    public ResponseEntity getMovieByName(@RequestParam(value = "name") String name) throws NotFoundException {
        Movie movie = movieController.getMovieByTitle(name);
        return (movie != null)? ResponseEntity.ok(movie) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(movie);
    }
    /*
        Obtiene una lista de Movies de un genero especifico
     */
    @RequestMapping(params="genre", method = RequestMethod.GET)
    public ResponseEntity<List<MovieDto>> getMoviesByGenre(@RequestParam(value = "genre") Integer genre){
        List<MovieDto> movies = movieController.getMoviesByGenre(genre)
                .stream()
                .map(MovieDto::new)
                .collect(Collectors.toList());
        return movies.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(movies) : ResponseEntity.ok(movies);
    }
    /*
        Obtiene una lista de Movies ordenadas por fecha ASC/DESC
     */
    @RequestMapping(params="order", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getMoviesByOrderDate(@RequestParam(value = "order") String order) {
        List<Movie> movies = movieController.getMoviesByOrder(order);
        return movies.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(movies) : ResponseEntity.ok(movies);
    }
    //*****************************---------------------------ABM---------------------------*******************************************************************************/

    /*
        Agrega una nueva Movie
    */
    @PostMapping("/")       //@RequestBody convierte la peticion web(Json) al obj Character
    public ResponseEntity addMovie(@RequestBody Movie movie) throws AlreadyExistsException {
        return ResponseEntity.created(RestUtil.getLocationMovie(movieController.addMovie(movie))).build();
    }

    /*
        Borra una Movie con un ID especifico
    */
    @DeleteMapping("/{idMovie}")
    public ResponseEntity deleteMovie(@PathVariable Integer idMovie) throws NotFoundException {
        movieController.removeMovie(idMovie);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    /*
        Actualiza los datos de una Movie
    */
    @PutMapping("/")
    public ResponseEntity updateMovie(@RequestBody Movie updatedMovie) throws NotFoundException {
        movieController.updateMovie(updatedMovie);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
