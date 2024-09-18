package com.example.mail_tracker.exceptions;

public class NoSuchPostalItemException extends RuntimeException {

    public NoSuchPostalItemException(int id) {
        super("Item with id " + id + " does not exist");
    }
}
