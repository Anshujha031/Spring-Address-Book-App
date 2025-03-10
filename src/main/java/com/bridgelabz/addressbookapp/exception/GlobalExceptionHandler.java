package com.bridgelabz.addressbookapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice  // This makes it a global exception handler
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) // Handles validation errors
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Extracting field-specific error messages
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST); // Returns 400 Bad Request
    }

    @ExceptionHandler(AddressBookException.class) // Handles Address Book ID not found errors
    public ResponseEntity<Map<String, String>> handleAddressBookException(AddressBookException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage()); // Custom error message

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); // Returns 404 Not Found
    }
}
