package com.clamos.io.collect.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, 4001, "err.invalid.input.value"), // Bad Request
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, 4002, "err.invalid.type.value"), // Bad Request
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST, 4003, "err.entity.not.found"), // Bad Request

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 4011, "err.unauthorized"), // The request requires an user authentication

    ACCESS_DENIED(HttpStatus.FORBIDDEN, 4031, "err.access.denied"), // Forbidden, Access is Denied
    NOT_FOUND(HttpStatus.NOT_FOUND, 4041, "err.not.found"), // Not found
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 4051, "err.method.not.allowed"), // 요청 방법이 서버에 의해 알려졌으나, 사용 불가능한 상태

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5001, "err.internal.server"), // Server Error
    ;

    private final HttpStatus status;
    private final int code;
    private final String message;
}
