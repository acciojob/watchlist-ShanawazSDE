package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    public MovieController(){
        movieService = new MovieService();
    }

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){

        try {
            movieService.addMovie(movie);

        }catch (MovieAlreadyExistsException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity("movie added",HttpStatus.OK);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        try {
            movieService.addDirector(director);

        }catch (DirectorAlreadyExistsException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
        return new ResponseEntity("director added ",HttpStatus.OK);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam String movie, @RequestParam String director){
        try {
            movieService.addMovieDirectorPair(movie,director);
        }catch (PairAlreadyExistsException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
        catch (MovieNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (DirectorNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("pair added",HttpStatus.OK);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name){
        Optional<Movie> op = movieService.getMovieByName(name);
        if(op.isPresent()) return new ResponseEntity(op.get(),HttpStatus.OK);
        return new ResponseEntity("movie doesn't exists",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name){
        Optional<Director> op = movieService.getDirectorByName(name);
        if(op.isPresent()) return new ResponseEntity(op.get(),HttpStatus.OK);
        return new ResponseEntity("director doesn't exists",HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable String director){
       List<String> list = movieService.getMoviesByDirectorName(director);
       if(list.isEmpty()) return new ResponseEntity("No movies found",HttpStatus.BAD_REQUEST);
       return new ResponseEntity(list,HttpStatus.OK);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String> list = movieService.findAllMovies();
        if(list.isEmpty()) return new ResponseEntity("There are no movies",HttpStatus.BAD_REQUEST);
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String name){
        try {
            movieService.deleteDirectorByName(name);
        }
        catch (DirectorNotFoundException e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("director and movies deleted",HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity("all directors and their movies deleted",HttpStatus.OK);
    }
}
