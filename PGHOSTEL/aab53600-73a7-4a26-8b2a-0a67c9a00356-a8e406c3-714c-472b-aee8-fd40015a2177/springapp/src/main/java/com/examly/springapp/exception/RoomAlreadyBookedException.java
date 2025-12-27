package com.examly.springapp.exception;

public class RoomAlreadyBookedException extends RuntimeException {

    public RoomAlreadyBookedException(String message) {
        super(message);
    }
}
