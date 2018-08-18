package uk.ac.ebi.codetest.exceptions;

import lombok.Getter;

import java.util.Map;

/**
 * Custom exception to mark any JSR-303 validation errors.
 *
 * @author Venkaiah Chowdary Koneru
 */
public class InvalidRequestException extends Exception {
    @Getter
    private final Map<String, String> errors;

    /**
     * constructor
     *
     * @param errors validation errors
     */
    public InvalidRequestException(Map<String, String> errors) {
        this.errors = errors;
    }
}
