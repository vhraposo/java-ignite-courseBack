package com.rocketseat.courseback.config.advicer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rocketseat.courseback.config.advicer.dto.ExceptionFieldDTO;
import com.rocketseat.courseback.config.advicer.dto.ExceptionInvalidFieldsDTO;
import com.rocketseat.courseback.config.advicer.dto.ExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionInvalidFieldsDTO> handleValidationException(MethodArgumentNotValidException exception) {
        var invalidFields = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new ExceptionFieldDTO(error.getField(), error.getDefaultMessage())).toList();
        var responseBody = new ExceptionInvalidFieldsDTO(HttpStatus.BAD_REQUEST.value(), invalidFields);
        return ResponseEntity.badRequest().body(responseBody);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        var responseBody = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), exception.getLocalizedMessage());
        return ResponseEntity.badRequest().body(responseBody);
    }

}
