package com.example.mintazh.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j(topic = "fileLogger")
public class GlobalExceptionHandler {

    // Validáláshoz szükséges kivételkezelő
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder errorMessage = new StringBuilder("A következő mezők érvényesítése nem sikerült:\n");
        fieldErrors.forEach(fieldError -> errorMessage.append(fieldError.getField()).append(" (").append(fieldError.getDefaultMessage()).append(")\n"));
        errorMessage.delete(errorMessage.length() - 1, errorMessage.length());
        log.error(errorMessage.toString());
        return errorMessage.toString();

    }
}
