package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public MovieService() {
        movieRepository = new MovieRepository();
    }

    public void addMovie(Movie movie) throws MovieAlreadyExistsException {
        if(movieRepository.movieAlreadyExists(movie)) throw new MovieAlreadyExistsException("movie already exists");
       else movieRepository.addMovie(movie);
    }

    public void addDirector(Director director) {
        if(movieRepository.directorAlreadyExists(director)) throw new DirectorAlreadyExistsException("director already exists");
        movieRepository.addDirector(director);
    }

    public void addMovieDirectorPair(String movie, String director) {
      Movie movieObj = null;
      Director directorObj = null;

      for(Movie m : movieRepository.getMovieSet()){
          if(m.getName().equals(movie)) movieObj = m;
      }

      for(Director d : movieRepository.getDirectorSet()){
          if(d.getName().equals(director)) directorObj = d;
      }

      if(movieObj == null) throw new MovieNotFoundException("movie not found");
      if(directorObj == null) throw new DirectorNotFoundException("director not found");

      Pair p = new Pair(movieObj,directorObj);
      if(movieRepository.pairAlreadyExists(p)) throw new PairAlreadyExistsException("Pair Already Exists");

      movieRepository.addPair(p);


    }

    public Optional<Movie> getMovieByName(String name) {
        Set<Movie> movieSet = movieRepository.getMovieSet();
        for(Movie movie : movieSet){
            if(name.equals(movie.getName())) return Optional.of(movie);
        }
        return Optional.empty();
    }

    public Optional<Director> getDirectorByName(String name) {
        Set<Director> directorSet = movieRepository.getDirectorSet();
        for(Director director : directorSet){
            if(name.equals(director.getName())) return Optional.of(director);
        }
        return Optional.empty();
    }

    public List<String> getMoviesByDirectorName(String director) {
        Set<Pair> pairSet = movieRepository.getPairSet();
        List<String> list = new ArrayList<>();
        for(Pair pair : pairSet){
            if(pair.getDirector().getName().equals(director)){
                list.add(pair.getMovie().getName());
            }
        }
        return list;
    }

    public List<String> findAllMovies() {

        return movieRepository.findAllMovies();
    }

    public void deleteDirectorByName(String name) {
        Director dir = null;
        for(Director director : movieRepository.getDirectorSet()){
            if(director.getName().equals(name)) dir = director;
        }
        if(dir == null) throw new DirectorNotFoundException("director not found");
        movieRepository.deleteDirectorByName(dir,true);
    }

    public void deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
    }
}
