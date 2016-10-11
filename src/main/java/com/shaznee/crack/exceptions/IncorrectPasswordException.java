package com.shaznee.crack.exceptions;

/**
 * Created by SHAZNEE on 08-Oct-16.
 */
public class IncorrectPasswordException extends Exception {

    public IncorrectPasswordException(String message) {
        super(message);
    }

    public IncorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
