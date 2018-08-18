package uk.ac.ebi.codetest.exceptions;

import lombok.Getter;

import java.util.Map;

/**
 *
 */
public class InvalidRequestException extends Exception {
    @Getter
    private final Map<String, String> errors;

    public InvalidRequestException(Map<String, String> errors) {
        this.errors = errors;
    }
}
