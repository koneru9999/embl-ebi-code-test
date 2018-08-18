package uk.ac.ebi.codetest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.codetest.dto.PersonDTO;
import uk.ac.ebi.codetest.dto.PersonWithIdDTO;
import uk.ac.ebi.codetest.events.PersonCreatedEvent;
import uk.ac.ebi.codetest.exceptions.InvalidRequestException;
import uk.ac.ebi.codetest.exceptions.ResourceNotFoundException;
import uk.ac.ebi.codetest.mappers.PersonMapper;
import uk.ac.ebi.codetest.model.Person;
import uk.ac.ebi.codetest.repository.PersonRepository;
import uk.ac.ebi.codetest.repository.PersonSpecifications;
import uk.ac.ebi.codetest.validation.ValidationUtil;

import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service implementation for person service.
 *
 * @author Venkaiah Chowdary Koneru
 * @see PersonService
 */
@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final Validator validator;
    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * @param personRepository
     * @param personMapper
     */
    @Autowired
    public PersonServiceImpl(PersonRepository personRepository,
                             PersonMapper personMapper,
                             Validator validator,
                             ApplicationEventPublisher applicationEventPublisher) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.validator = validator;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public PersonWithIdDTO createPerson(PersonDTO personDTO) throws InvalidRequestException {

        // Validate DTO
        Map<String, String> errors = ValidationUtil.validateDTO(personDTO, validator);

        // If validation errors, throw with validation constraints
        if (!errors.isEmpty()) {
            throw new InvalidRequestException(errors);
        }

        Person savedPerson = personRepository.save(personMapper.personDtoToPerson(personDTO));

        // Publish created event
        applicationEventPublisher.publishEvent(new PersonCreatedEvent(this, savedPerson));

        return personMapper.personToPersonIdDTO(savedPerson);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public PersonWithIdDTO updatePerson(PersonWithIdDTO personDTO) throws InvalidRequestException {
        // Validate DTO
        Map<String, String> errors = ValidationUtil.validateDTO(personDTO, validator);

        // If validation errors, throw with validation constraints
        if (!errors.isEmpty()) {
            throw new InvalidRequestException(errors);
        }

        Person person = personRepository.findByIdAndDeletedFalse(personDTO.getId())
                .orElseThrow(ResourceNotFoundException::new);

        person.setAge(personDTO.getAge());
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setFavouriteColour(personDTO.getFavouriteColour());

        Person updatedPerson = personRepository.save(person);

        // Publish updated event
        applicationEventPublisher.publishEvent(new PersonCreatedEvent(this, updatedPerson));

        return personMapper.personToPersonIdDTO(updatedPerson);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deletePerson(Long id) {
        Person person = personRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(ResourceNotFoundException::new);
        person.setDeleted(Boolean.TRUE);
        Person deletedPerson = personRepository.save(person);

        // Publish deleted event
        applicationEventPublisher.publishEvent(new PersonCreatedEvent(this, deletedPerson));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonDTO findPersonById(Long id) {
        Person person = personRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(ResourceNotFoundException::new);

        return personMapper.personToPersonDTO(person);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonDTO> findAll() {
        List<Person> list = personRepository.findAll(PersonSpecifications.findAllNonDeleted());

        if (list != null && !list.isEmpty()) {
            return list
                    .stream()
                    .map(personMapper::personToPersonDTO)
                    .collect(Collectors.toList());
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PersonDTO> findPersonByFirstnameOrLastName(String searchToken, Pageable properties) {
        return personRepository.findAll(PersonSpecifications.personByFirstnameOrLastname(searchToken), properties)
                .map(personMapper::personToPersonDTO);
    }
}
