package com.driver;

import java.util.Objects;

public class Pair {
    private Movie movie;
    private Director director;
    public Pair(Movie movie, Director director) {
        this.movie = movie;
        this.director = director;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair pair = (Pair) o;
        return getMovie().equals(pair.getMovie()) && getDirector().equals(pair.getDirector());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovie(), getDirector());
    }
}
