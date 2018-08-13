package com.schoolapp.core.exceptions;

public class EmailExistsException extends Throwable {

    public EmailExistsException() {
        super();
    }

    public EmailExistsException(String message) {
        super(message);
    }
}
