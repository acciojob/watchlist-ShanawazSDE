package com.driver;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String s) {
        super(s);
    }
}
