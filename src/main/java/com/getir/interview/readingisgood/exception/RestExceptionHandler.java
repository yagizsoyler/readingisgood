package com.getir.interview.readingisgood.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        return buildResponseEntity(ApiError.builder().status(NOT_FOUND).message(ex.getMessage()).timestamp(LocalDateTime.now()).build());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    protected ResponseEntity<Object> handleEmailAlreadyExistsException(
    		EmailAlreadyExistsException ex) {
        return buildResponseEntity(ApiError.builder().status(NOT_ACCEPTABLE).message(ex.getMessage()).timestamp(LocalDateTime.now()).build());
    }
    
    @ExceptionHandler(InsufficentBookStockException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    protected ResponseEntity<Object> handleInsufficentBookStockException(
    		InsufficentBookStockException ex) {
        return buildResponseEntity(ApiError.builder().status(NOT_ACCEPTABLE).message(ex.getMessage()).timestamp(LocalDateTime.now()).build());
    }
    
    @ExceptionHandler(NegativeBookStockException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    protected ResponseEntity<Object> handleNegativeBookStockException(
    		NegativeBookStockException ex) {
        return buildResponseEntity(ApiError.builder().status(NOT_ACCEPTABLE).message(ex.getMessage()).timestamp(LocalDateTime.now()).build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return buildResponseEntity(ApiError.builder().status(BAD_REQUEST).message(errorMessage).timestamp(LocalDateTime.now()).build());
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
