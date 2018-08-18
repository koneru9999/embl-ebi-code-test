package uk.ac.ebi.codetest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uk.ac.ebi.codetest.dto.PersonDTO;
import uk.ac.ebi.codetest.dto.PersonWithIdDTO;
import uk.ac.ebi.codetest.exceptions.InvalidRequestException;

import java.util.List;

/**
 * @author Venkaiah Chowdary Koneru
 */
public interface PersonService {

    /**
     * @param personDTO
     * @return
     */
    PersonWithIdDTO createPerson(PersonDTO personDTO) throws InvalidRequestException;

    /**
     * @param personDTO
     * @return
     */
    PersonWithIdDTO updatePerson(PersonWithIdDTO personDTO) throws InvalidRequestException;

    /**
     * @param id
     * @return
     */
    void deletePerson(Long id);

    /**
     * fetches person as DTO object for a given id
     *
     * @param id
     * @return
     */
    PersonDTO findPersonById(Long id);

    /**
     * @return
     */
    List<PersonDTO> findAll();

    /**
     * This method retrieves the list of persons for the provided search token.
     * Results containing search token (case in-sensitive) either in first name or last name will be returned.
     *
     * @param searchToken
     * @param properties
     * @return
     */
    Page<PersonDTO> findPersonByFirstnameOrLastName(String searchToken, Pageable properties);
}
