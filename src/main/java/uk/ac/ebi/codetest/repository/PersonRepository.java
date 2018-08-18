package uk.ac.ebi.codetest.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.codetest.model.Person;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Venkaiah Chowdary Koneru
 */
@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, UUID>,
        JpaSpecificationExecutor<Person> {

    /**
     * @param id
     * @return
     */
    Optional<Person> findByIdAndDeletedFalse(Long id);
}
