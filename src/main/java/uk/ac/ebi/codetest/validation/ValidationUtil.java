package uk.ac.ebi.codetest.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Validation Util class.
 *
 * @author Venkaiah Chowdary Koneru
 */
@Slf4j
public final class ValidationUtil {
    private static final String VIOLATION_TRACE_MSG = "Bean Validation Constraint violation for property [%s] with message [%s]";

    /**
     * Constructor. Private to prevent unnecessary instantiation.
     */
    private ValidationUtil() {
    }

    /**
     * Validates a DTO Object against all validation groups. <br/> This helper method is intended for grabbing all the
     * messages across all the groups instead of one group single time.
     *
     * @param dto       the object that need to be validated
     * @param validator the JSR-303 Validator instance
     *
     * @return A Map of errors with property name as keys and error codes as values
     */
    public static Map<String, String> validateDTO(final Object dto,
                                                  final Validator validator) {
        Assert.notNull(validator, "An empty javax.validator instance is supplied");

        Map<String, String> errors = new LinkedHashMap<>();

        Set<ConstraintViolation<Object>> violations = validator.validate(dto,
                Default.class);
        processConstraintViolations(violations, errors);

        violations = validator.validate(dto, SecondGroup.class);
        processConstraintViolations(violations, errors);
        return errors;
    }

    /**
     * helper method to process constraint violations and put every violation into a Map
     *
     * @param violations The JSR-303 ConstraintViolation results
     * @param errors     a non-null hash map to store the processed violations
     */
    public static void processConstraintViolations(
            final Set<ConstraintViolation<Object>> violations,
            final Map<String, String> errors) {
        Assert.notNull(errors, "an empty map is supplied");
        for (ConstraintViolation<Object> violation : violations) {
            if (!errors.containsKey(violation.getPropertyPath().toString())) {
                if (log.isTraceEnabled()) {
                    log.trace(String.format(VIOLATION_TRACE_MSG, violation
                            .getPropertyPath().toString(), violation
                            .getMessage()));
                }
                errors.put(violation.getPropertyPath().toString(),
                        violation.getMessage());
            }
        }
    }
}