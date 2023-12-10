package com.vakaliuk.mathhelper.exception;

import lombok.Getter;

@Getter
public class RootNotCorrectException extends RuntimeException {
    private final String message;
    public RootNotCorrectException(String message) {
        this.message = message;
    }
}
