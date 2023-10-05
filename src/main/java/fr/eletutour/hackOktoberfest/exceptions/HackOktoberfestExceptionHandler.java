package fr.eletutour.hackOktoberfest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HackOktoberfestExceptionHandler {

    @ExceptionHandler(UserException.class)
    private ResponseEntity<ErrorResponse> handleUserException(UserException ue) {
        return ResponseEntity.status(ue.getStatus()).body(new ErrorResponse(ue.getStatus(), ue.getMessage()));
    }

    @ExceptionHandler(BreweryException.class)
    private ResponseEntity<ErrorResponse> handleBreweryException(BreweryException be) {
        return ResponseEntity.status(be.getStatus()).body(new ErrorResponse(be.getStatus(), be.getMessage()));
    }

}
