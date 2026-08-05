package com.shaurya.librarymanagementsystem.exception;

import com.shaurya.librarymanagementsystem.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthorNotFoundException(AuthorNotFoundException e, HttpServletRequest request){
        ApiErrorResponse response = new ApiErrorResponse(
                                        LocalDateTime.now(),
                                        HttpStatus.NOT_FOUND.value(),
                                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                                        e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleBookNotFoundException(BookNotFoundException e, HttpServletRequest request){
        ApiErrorResponse response = new ApiErrorResponse(
                                        LocalDateTime.now(),
                                        HttpStatus.NOT_FOUND.value(),
                                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                                        e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(AuthorDeletionException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthorDeletionException(AuthorDeletionException e, HttpServletRequest request){
        ApiErrorResponse response = new ApiErrorResponse(
                                        LocalDateTime.now(),
                                        HttpStatus.CONFLICT.value(),
                                        HttpStatus.CONFLICT.getReasonPhrase(),
                                        e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(DuplicateAuthorException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateAuthorException(DuplicateAuthorException e, HttpServletRequest request){
        ApiErrorResponse response = new ApiErrorResponse(
                                        LocalDateTime.now(),
                                        HttpStatus.CONFLICT.value(),
                                        HttpStatus.CONFLICT.getReasonPhrase(),
                                        e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(DuplicateIsbnException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateIsbnException(DuplicateIsbnException e, HttpServletRequest request){
        ApiErrorResponse response = new ApiErrorResponse(
                                        LocalDateTime.now(),
                                        HttpStatus.CONFLICT.value(),
                                        HttpStatus.CONFLICT.getReasonPhrase(),
                                        e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateEmailException(DuplicateEmailException e, HttpServletRequest request){
        ApiErrorResponse response = new ApiErrorResponse(
                                        LocalDateTime.now(),
                                        HttpStatus.CONFLICT.value(),
                                        HttpStatus.CONFLICT.getReasonPhrase(),
                                        e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleMemberNotFoundException(MemberNotFoundException e, HttpServletRequest request){
        ApiErrorResponse response = new ApiErrorResponse(
                                        LocalDateTime.now(),
                                        HttpStatus.NOT_FOUND.value(),
                                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                                        e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
