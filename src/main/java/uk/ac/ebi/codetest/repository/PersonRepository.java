package uk.ac.ebi.codetest.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.codetest.model.Person;

import java.util.Optional;
import java.util.UUID;

/**
 * Person repository
 *
 * @author Venkaiah Chowdary Koneru
 */
@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, UUID>,
        JpaSpecificationExecutor<Person> {

    /**
     * fetch a person for the supplied argument id. Deleted person(s) are excluded.
     *
     * @param id id of person to retrieve
     *
     * @return Person object
     */
    Optional<Person> findByIdAndDeletedFalse(Long id);
}
