package com.aglayatech.mundo3.error.exceptions;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message);
    }
}
