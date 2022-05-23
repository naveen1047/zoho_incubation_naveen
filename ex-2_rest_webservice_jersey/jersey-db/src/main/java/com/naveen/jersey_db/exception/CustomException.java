package com.naveen.jersey_db.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
