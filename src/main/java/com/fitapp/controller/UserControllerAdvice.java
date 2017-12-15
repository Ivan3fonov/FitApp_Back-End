package com.fitapp.controller;

import com.fitapp.model.ValidationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = UserController.class)
public class UserControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Errors errors = exception.getBindingResult();
        
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");

        for (ObjectError objectError : errors.getGlobalErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        for (FieldError fieldError : errors.getFieldErrors()) {
            error.addValidationError(fieldError.getField() + ": " +
                    fieldError.getDefaultMessage());
        }
        return super.handleExceptionInternal(exception, error, headers, status, request);
    }
}

