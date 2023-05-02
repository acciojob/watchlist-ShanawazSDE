package com.driver;

import java.util.Objects;

public class Director {
    private String name;
    private int numberOfMovies;
    private double imdbRating;

    public Director(){
        name = "";
    }

    public Director(String name, int numberOfMovies, double imdbRating){
        this.name = name;
        this.numberOfMovies = numberOfMovies;
        this.imdbRating = imdbRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfMovies() {
        return numberOfMovies;
    }

    public void setNumberOfMovies(int numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Director)) return false;
        Director director = (Director) o;
        return getNumberOfMovies() == director.getNumberOfMovies() && Double.compare(director.getImdbRating(), getImdbRating()) == 0 && getName().equals(director.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNumberOfMovies(), getImdbRating());
    }
}
