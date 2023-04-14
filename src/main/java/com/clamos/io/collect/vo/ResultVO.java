package com.clamos.io.collect.vo;

import com.clamos.io.collect.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class ResultVO<T> implements Serializable {

    private final int code;
    private final String message;
    private final T data;

    public ResultVO(int code, T data) {
        this(code, null, data);
    }

    public ResultVO(T data) {
        this(0, data);
    }

    public ResultVO(ErrorCode errorCode, T data) {
        this(errorCode.getCode(), errorCode.getMessage(), data);
    }
}
