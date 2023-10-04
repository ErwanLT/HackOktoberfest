package fr.eletutour.hackOktoberfest.exceptions;

import org.springframework.http.HttpStatus;

public class BreweryException extends RuntimeException {

    private HttpStatus status;

    public BreweryException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
