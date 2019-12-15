package mpi.controller;

import mpi.exception.EntityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = EntityException.class)
    public ResponseEntity<Object> exception(EntityException exception) {
        return new ResponseEntity<>(exception, exception.getHttpStatus());
    }
}
