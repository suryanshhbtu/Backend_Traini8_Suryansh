package com.buyogo.assignment.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles validation exceptions for @Valid annotated requests.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            FieldError fieldError = (FieldError) error;
            String fieldName = fieldError.getField();
            Object rejectedValue = fieldError.getRejectedValue();
            String errorMessage = fieldError.getDefaultMessage();

            // Log detailed error information
            logger.error("Validation failed for field '{}', rejected value '{}': {}", fieldName, rejectedValue, errorMessage);

            // Store error details in the response
            Map<String, Object> fieldDetails = new HashMap<>();
            fieldDetails.put("rejectedValue", rejectedValue);
            fieldDetails.put("message", errorMessage);

            errors.put(fieldName, fieldDetails);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles validation exceptions from service layer (@Validated in @Service).
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, Object> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            Object invalidValue = violation.getInvalidValue();
            String errorMessage = violation.getMessage();

            // Log the violation details
            logger.error("Constraint violation on field '{}', invalid value '{}': {}", fieldName, invalidValue, errorMessage);

            // Store details in the response
            Map<String, Object> fieldDetails = new HashMap<>();
            fieldDetails.put("invalidValue", invalidValue);
            fieldDetails.put("message", errorMessage);

            errors.put(fieldName, fieldDetails);
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Generic exception handler for catching other unexpected exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalExceptions(Exception ex) {
        logger.error("Unexpected error occurred: {}", ex.getMessage(), ex);

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "An unexpected error occurred");
        errorResponse.put("message", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
