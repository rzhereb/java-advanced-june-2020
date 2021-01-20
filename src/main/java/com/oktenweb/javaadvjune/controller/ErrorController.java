package com.oktenweb.javaadvjune.controller;

import com.oktenweb.javaadvjune.dto.BadRequestException;
import com.oktenweb.javaadvjune.dto.ResponseErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = parseValidationException(ex);
        return new ResponseErrorDto(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), message);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseErrorDto handleBadRequestException(BadRequestException ex) {
        log.info("Handled BadRequestException: {}", ex.getMessage());
        return new ResponseErrorDto(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), ex.getMessage());
    }

    private String parseValidationException(MethodArgumentNotValidException ex) {
        final List<String> defaultMessages = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return defaultMessages.toString();
    }
}
