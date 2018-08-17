package uk.ac.ebi.codetest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import uk.ac.ebi.codetest.dto.PersonDTO;
import uk.ac.ebi.codetest.model.Person;

/**
 * @author Venkaiah Chowdary Koneru
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mappings({
            @Mapping(target = "id", expression = "java(entity.getId().toString())")
    })
    PersonDTO personToPersonDTO(Person entity);
}
