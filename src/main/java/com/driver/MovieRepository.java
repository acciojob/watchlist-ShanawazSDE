package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class MovieRepository {
   // List<Movie> movieList = new ArrayList<>();
  private   Set<Movie> movieSet = new HashSet<>();
   private Set<Pair> pairSet = new HashSet<>();
   // List<Director> directorList = new ArrayList<>();
  private   Set<Director> directorSet = new HashSet<>();
  private Set<Pair> pairSetHelper = null;

    public void addMovie(Movie movie) {
      //  movieList.add(movie);
        movieSet.add(movie);
    }

    public boolean movieAlreadyExists(Movie movie) {
       // System.out.println(movieSet.contains(movie));
       return movieSet.contains(movie);

    }

    public boolean directorAlreadyExists(Director director) {
       return directorSet.contains(director);
    }


    public void addDirector(Director director) {
        //directorList.add(director);
        directorSet.add(director);
    }

    public boolean pairAlreadyExists(Pair p) {
        return pairSet.contains(p);

    }

    public void addPair(Pair p) {
        pairSet.add(p);
    }

    public Set<Movie> getMovieSet() {
        return movieSet;
    }

    public void setMovieSet(Set<Movie> movieSet) {
        this.movieSet = movieSet;
    }

    public Set<Pair> getPairSet() {
        return pairSet;
    }

    public void setPairSet(Set<Pair> pairSet) {
        this.pairSet = pairSet;
    }

    public Set<Director> getDirectorSet() {
        return directorSet;
    }

    public void setDirectorSet(Set<Director> directorSet) {
        this.directorSet = directorSet;
    }

    public List<Movie> findAllMovies() {
        List<Movie> list = new ArrayList<>();
         list.addAll(movieSet);
        return list;
    }

    public void deleteDirectorByName(Director dir,boolean removeDirectorFromDirectorSet) {

        pairSetHelper = new HashSet<>();
        for(Pair p : pairSet){
            if(p.getDirector().equals(dir)){
                movieSet.remove(p.getMovie());

            }else pairSetHelper.add(p);
        }
        if(removeDirectorFromDirectorSet)
            directorSet.remove(dir);
        pairSet = pairSetHelper;
//        System.out.println(pairSetHelper);
    }

    public void deleteAllDirectors() {
        //we have deleteDirectorByName method so we call this for every director
        //the only catch is in that method we are doing directorSet.remove(dir);
        //which will cause concurrentmodificationException because we are iterating
        //through the same set int this method and trying to modify the same set from
        //deleteDirectorByName method. to overcome this we use a boolean to decide whether to
        //execute directorSet.remove(dir);. when deleteDirectorByName is called from service object
        // we should execute the statement and when current method call it, it shouldn't xcute it to avoid
        //exception
        for(Director director : directorSet){
            deleteDirectorByName(director,false);
        }
        directorSet.clear();

    }
}




















