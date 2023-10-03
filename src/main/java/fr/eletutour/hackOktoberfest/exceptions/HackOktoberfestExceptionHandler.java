package fr.eletutour.hackOktoberfest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HackOktoberfestExceptionHandler {

    @ExceptionHandler(UserException.class)
    private ResponseEntity<Object> handleUserException(UserException ue) {
        return ResponseEntity.status(ue.getStatus()).body(new ErrorResponse(ue.getStatus(), ue.getMessage()));
    }

    private static class ErrorResponse {

        private HttpStatus status;
        private String message;
        public ErrorResponse(HttpStatus status, String message) {
            this.status = status;
            this.message = message;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public void setStatus(HttpStatus status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
