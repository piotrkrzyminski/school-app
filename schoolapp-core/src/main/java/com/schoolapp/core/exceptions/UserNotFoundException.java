package com.schoolapp.core.exceptions;

public class UserNotFoundException extends Throwable {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
