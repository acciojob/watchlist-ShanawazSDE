package com.driver;

import java.util.Objects;

public class Movie {
    private String name;
    private int durationInMinutes;
    private double imdbRating;

    ////////////////////????????????????????????????????////////////////////////////
    public Movie(){
        name = "";
    }

    public Movie(String name, int durationInMinutes, double imdbRating){
        this.name = name;
        this.durationInMinutes = durationInMinutes;
        this.imdbRating = imdbRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
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
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return getDurationInMinutes() == movie.getDurationInMinutes() && Double.compare(movie.getImdbRating(), getImdbRating()) == 0 && getName().equals(movie.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDurationInMinutes(), getImdbRating());
    }
}
