package uk.ac.ebi.codetest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uk.ac.ebi.codetest.exceptions.InvalidRequestException;

import java.util.Map;

/**
 * @author Venkaiah Chowdary Koneru
 */
@ControllerAdvice
@RestController
public class InvalidRequestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<Map<String, String>> handleInvalidRequestException(InvalidRequestException ex,
                                                                                   WebRequest request) {
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
