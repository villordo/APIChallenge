package com.example.demo.exceptions;

public class AlreadyExistsException extends Throwable {
    public AlreadyExistsException(String msg) {
        super(msg);
    }
}
