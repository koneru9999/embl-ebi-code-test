package uk.ac.ebi.codetest.mappers;

import org.mapstruct.Mapper;
import uk.ac.ebi.codetest.dto.PersonDTO;
import uk.ac.ebi.codetest.dto.PersonWithIdDTO;
import uk.ac.ebi.codetest.model.Person;

/**
 * Mapper interface for person dto <--> person entity.
 *
 * @author Venkaiah Chowdary Koneru
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDTO personToPersonDTO(Person entity);

    Person personDtoToPerson(PersonDTO personDTO);

    PersonWithIdDTO personToPersonIdDTO(Person entity);
}
