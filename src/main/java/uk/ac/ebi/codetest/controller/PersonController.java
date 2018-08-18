package uk.ac.ebi.codetest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.codetest.dto.PersonDTO;
import uk.ac.ebi.codetest.dto.PersonDTOList;
import uk.ac.ebi.codetest.dto.PersonWithIdDTO;
import uk.ac.ebi.codetest.exceptions.InvalidRequestException;
import uk.ac.ebi.codetest.service.PersonService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    /**
     * @param personService
     */
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * @param personDTO
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGES')")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonWithIdDTO storePerson(@RequestBody PersonDTO personDTO) throws InvalidRequestException {
        return personService.createPerson(personDTO);
    }

    /**
     * @param personDTO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGES')")
    public PersonWithIdDTO updatePerson(@RequestBody PersonWithIdDTO personDTO) throws InvalidRequestException {
        return personService.updatePerson(personDTO);
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('READ_PRIVILEGES')")
    public PersonDTO retrievePersonById(@PathVariable Long id) {
        return personService.findPersonById(id);
    }

    /**
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('READ_PRIVILEGES')")
    public PersonDTOList retrieveAllPersons() {
        List<PersonDTO> list = personService.findAll();

        if (list != null) {
            return new PersonDTOList(list);
        }

        return new PersonDTOList(new ArrayList<>());
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGES')")
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
