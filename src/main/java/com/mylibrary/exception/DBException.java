package com.mylibrary.exception;

public class DBException extends RuntimeException {
    public DBException(String message) {
        super(message);
    }
}
