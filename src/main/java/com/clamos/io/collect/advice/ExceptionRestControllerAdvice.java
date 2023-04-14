package com.clamos.io.collect.advice;

import com.clamos.io.collect.exception.ErrorCode;
import com.clamos.io.collect.vo.ResultVO;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ExceptionRestControllerAdvice {
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO<Object> handleMethodArgumentNotValidException(BindException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        return new ResultVO<>(ErrorCode.INVALID_INPUT_VALUE, error.getDefaultMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO<String> handleMethodArgumentNotValidException(MethodArgumentTypeMismatchException e) {
        return new ResultVO<>(ErrorCode.INVALID_TYPE_VALUE, e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResultVO<String> handleAccessDeniedException(HttpServletResponse response, AccessDeniedException e) {
        response.setStatus(403);
        return new ResultVO<>(ErrorCode.ACCESS_DENIED, e.getMessage());
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResultVO<String> handleNotFoundException(ChangeSetPersister.NotFoundException e) {
        return new ResultVO<>(ErrorCode.NOT_FOUND, e.getMessage());
    }
}
