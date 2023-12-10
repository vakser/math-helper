package com.vakaliuk.mathhelper.exception;

import lombok.Getter;

@Getter
public class ExpressionNotValidException extends RuntimeException {
    private final String message;
    public ExpressionNotValidException(String message) {
        this.message = message;
    }
}
