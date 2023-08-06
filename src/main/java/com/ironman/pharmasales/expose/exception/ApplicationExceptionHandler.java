package com.ironman.pharmasales.expose.exception;

import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import com.ironman.pharmasales.shared.exception.entity.ArgumentNotValidError;
import com.ironman.pharmasales.shared.exception.entity.GeneralError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArgumentNotValidError> handleInvalidArgument(MethodArgumentNotValidException ex) {
        ArgumentNotValidError response = new ArgumentNotValidError();
        Map<String, String> error = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> {
                    error.put(fieldError.getField(), fieldError.getDefaultMessage());
                });

        response.setMessage("Ingrese todos los datos requeridos");
        response.setError(error);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<GeneralError> handleDataNotFound(DataNotFoundException ex) {
        GeneralError response = new GeneralError();

        response.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
