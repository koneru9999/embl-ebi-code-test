package uk.ac.ebi.codetest.dto;

import lombok.Data;
import uk.ac.ebi.codetest.validation.SecondGroup;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Venkaiah Chowdary Koneru
 */
@Data
public class PersonDTO {
    private String id;

    @NotEmpty(message = "firstName.null")
    @Size(min = 3, max = 150, message = "firstName.length", groups = SecondGroup.class)
    private String firstName;

    @NotEmpty(message = "lastName.null")
    @Size(min = 3, max = 150, message = "lastName.length", groups = SecondGroup.class)
    private String lastName;

    @NotEmpty(message = "age.null")
    @Size(min = 1, max = 150, message = "age.length", groups = SecondGroup.class)
    private Integer age;

    private Boolean deleted = Boolean.FALSE;
    private Date createdDate;
    private Date lastModifiedDate;
}
