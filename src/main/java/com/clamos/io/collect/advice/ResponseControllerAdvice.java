package com.clamos.io.collect.advice;

import com.clamos.io.collect.exception.APIException;
import com.clamos.io.collect.vo.ResultVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(basePackages = {"com.clamos.io"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    final ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        Class<?> parameterType = returnType.getParameterType();

        if (parameterType.equals(ResponseEntity.class)) {
            return false;
        }

        return !parameterType.equals(ResultVO.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {


        if (returnType.getGenericParameterType().equals(ResponseEntity.class)) {

            return body;
        } else if (returnType.getGenericParameterType().equals(String.class)) {

            try {
                return objectMapper.writeValueAsString(new ResultVO<>(body));
            } catch (JsonProcessingException e) {
                throw new APIException("Return String Type Error");
            }
        }

        return new ResultVO<>(body);
    }

}
