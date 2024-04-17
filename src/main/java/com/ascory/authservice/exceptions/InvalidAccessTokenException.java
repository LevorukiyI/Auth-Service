package com.ascory.authservice.exceptions;

public class InvalidAccessTokenException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Invalid discord access token.";

    public InvalidAccessTokenException(String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

    public InvalidAccessTokenException(){
        super(DEFAULT_MESSAGE);
    }
}
