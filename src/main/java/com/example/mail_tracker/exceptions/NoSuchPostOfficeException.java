package com.example.mail_tracker.exceptions;

public class NoSuchPostOfficeException extends RuntimeException {

    public NoSuchPostOfficeException(int id) {
        super("Post office with id " + id + " does not exist");
    }
}
