package com.driver;

public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException(String s) {
        super(s);
    }
}
