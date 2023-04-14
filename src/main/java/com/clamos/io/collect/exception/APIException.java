package com.clamos.io.collect.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class APIException extends RuntimeException {
    private final int code;
    private final String message;

    public APIException(String message) {
        this(5000, message);
    }

    public APIException() {
        this(5000, "API Error");
    }
}
