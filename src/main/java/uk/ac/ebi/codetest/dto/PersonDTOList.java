package uk.ac.ebi.codetest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author Venkaiah Chowdary Koneru
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PersonDTOList {
    private List<PersonDTO> person;
}
