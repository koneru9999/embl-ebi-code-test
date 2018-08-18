package uk.ac.ebi.codetest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PersonWithIdDTO extends PersonDTO {
    @JsonProperty("id")
    private Long id;
}
