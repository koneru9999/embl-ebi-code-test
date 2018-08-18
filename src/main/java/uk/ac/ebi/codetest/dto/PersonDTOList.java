package uk.ac.ebi.codetest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PersonDTOList {
    private List<PersonDTO> person;
}
