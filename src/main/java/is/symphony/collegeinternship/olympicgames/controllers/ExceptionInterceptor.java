package is.symphony.collegeinternship.olympicgames.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<String> handleConstraintViolationExceptions(
            ConstraintViolationException ex) {
        String exceptionResponse = String.format("Invalid input parameters: %s\n", ex.getMessage());
        return new ResponseEntity<String>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<String> handleDataIntegrityViolationExceptions(
            DataIntegrityViolationException e) {
        String exceptionResponse = String.format("User with same badge number exists: %s\n", e.getMessage());
        return new ResponseEntity<String>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
