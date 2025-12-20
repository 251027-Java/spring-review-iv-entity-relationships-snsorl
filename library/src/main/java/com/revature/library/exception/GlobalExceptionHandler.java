package com.revature.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler - TODO: Complete the exception handlers
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
     public ResponseEntity<Map<String, Object>>
     handleBookNotFound(BookNotFoundException ex) {
     return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
     }

    // TODO: Handle BookNotAvailableException
     @ExceptionHandler(BookNotAvailableException.class)
     public ResponseEntity<Map<String, Object>>
       handleBookNotAvailable(BookNotAvailableException ex) {
       return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
     }

    // TODO: Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<Map<String, Object>>
       handleValidationErrors(MethodArgumentNotValidException ex) {
     // Extract validation errors and build response
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = (fieldError != null) ? fieldError.getDefaultMessage() : "Validation failed";
        return buildErrorResponse(message, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
