package uk.ac.ebi.codetest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uk.ac.ebi.codetest.dto.PersonDTO;
import uk.ac.ebi.codetest.dto.PersonWithIdDTO;
import uk.ac.ebi.codetest.exceptions.InvalidRequestException;

import java.util.List;

/**
 * Person service
 *
 * @author Venkaiah Chowdary Koneru
 */
public interface PersonService {

    /**
     * stores a person
     *
     * @param personDTO person object to store
     *
     * @return saved person object with generated id.
     *
     * @throws InvalidRequestException if there are any validation errors
     */
    PersonWithIdDTO createPerson(PersonDTO personDTO) throws InvalidRequestException;

    /**
     * Updates a person
     *
     * @param personDTO person object to update
     *
     * @return updated person object.
     *
     * @throws InvalidRequestException if there are any validation errors
     */
    PersonWithIdDTO updatePerson(PersonWithIdDTO personDTO) throws InvalidRequestException;

    /**
     * Marks a person as deleted. This is a soft-delete operation.
     *
     * @param id id of the person to delete.
     */
    void deletePerson(Long id);

    /**
     * fetches person as DTO object for a given id
     *
     * @param id id of the person to fetch
     *
     * @return person object
     */
    PersonDTO findPersonById(Long id);

    /**
     * @return all non-deleted persons
     */
    List<PersonDTO> findAll();

    /**
     * This method retrieves the list of persons for the provided search token. Results containing search token (case
     * in-sensitive) either in first name or last name will be returned.
     *
     * @param searchToken
     * @param properties
     *
     * @return
     */
    Page<PersonDTO> findPersonByFirstnameOrLastName(String searchToken, Pageable properties);
}
