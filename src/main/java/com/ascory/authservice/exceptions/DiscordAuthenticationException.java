package com.ascory.authservice.exceptions;

public class DiscordAuthenticationException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Discord authentication failed.";

    public DiscordAuthenticationException(String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

    public DiscordAuthenticationException(){
        super(DEFAULT_MESSAGE);
    }
}
