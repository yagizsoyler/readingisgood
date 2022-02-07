package com.getir.interview.readingisgood.exception;

@SuppressWarnings("serial")
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message )
    {
        super( message );
    }
}