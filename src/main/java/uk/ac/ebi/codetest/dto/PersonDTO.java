package uk.ac.ebi.codetest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import uk.ac.ebi.codetest.validation.SecondGroup;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Person DTO
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@EqualsAndHashCode
@ToString
public class PersonDTO {
    @NotEmpty(message = "firstName.null")
    @Size(min = 3, max = 150, message = "firstName.length", groups = SecondGroup.class)
    @JsonProperty("first_name")
    private String firstName;

    @NotEmpty(message = "lastName.null")
    @Size(min = 3, max = 150, message = "lastName.length", groups = SecondGroup.class)
    @JsonProperty("last_name")
    private String lastName;

    @NotEmpty(message = "age.null")
    private String age;

    @JsonProperty("favourite_colour")
    @NotNull(message = "favouriteColour.null")
    private String favouriteColour;
}
