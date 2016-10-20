package com.shaznee.cracker.exceptions;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class CrackerException extends Exception {

    public CrackerException(String message) {
        super(message);
    }

    public CrackerException(String message, Throwable cause) {
        super(message, cause);
    }
}
