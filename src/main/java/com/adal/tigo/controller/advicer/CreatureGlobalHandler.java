package com.adal.tigo.controller.advicer;


import com.adal.tigo.Exception.CreatureException;
import com.adal.tigo.model.Creature;
import com.adal.tigo.model.GenericResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class CreatureGlobalHandler {

    private String getBodyErrors(List<FieldError> fieldErrors) {
        StringBuilder errors = new StringBuilder();
        for (FieldError fieldError: fieldErrors) {
            errors.append("Attribute [").append(fieldError.getField()).append("] : [").append(fieldError.getDefaultMessage()).append("]. ");
        }
        return errors.toString();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> handleValidation(MethodArgumentNotValidException e) throws CreatureException {
        return ResponseEntity.badRequest().body(
                new GenericResponse(
                        Instant.now().toString(),
                        "BAD_REQUEST",
                        getBodyErrors(e.getBindingResult().getFieldErrors()),
                        400
                )
        );

    }

    @ExceptionHandler(CreatureException.class)
    public ResponseEntity<GenericResponse> globalHandler(CreatureException e) {
        GenericResponse response = new GenericResponse(
                e.getTimestamp(),
                e.getCode(),
                e.getDetails(),
                e.getHttpStatus()
        );

        return ResponseEntity
                .status(HttpStatusCode.valueOf(response.getStatus()))
                .body(response);
    }


}
