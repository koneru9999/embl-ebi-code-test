package uk.ac.ebi.codetest.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import uk.ac.ebi.codetest.model.Person;
import uk.ac.ebi.codetest.model.Person_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Venkaiah Chowdary Koneru
 */
public final class PersonSpecifications {

    /**
     *
     */
    private PersonSpecifications() {
    }

    /**
     * @return
     */
    public static Specification<Person> findAllNonDeleted() {
        return (Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.isFalse(root.get(Person_.deleted)));

            return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));
        };
    }

    /**
     * @param searchToken
     * @return
     */
    public static Specification<Person> personByFirstnameOrLastname(String searchToken) {
        return (Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.isFalse(root.get(Person_.deleted)));

            if (StringUtils.hasText(searchToken)) {
                String containsLikePattern = getContainsLikePattern(searchToken);
                predicates.add(cb.or(cb.like(cb.lower(root.get(Person_.firstName)), containsLikePattern),
                        cb.like(cb.lower(root.get(Person_.lastName)), containsLikePattern)));
            }

            return cb.and(predicates.stream().toArray(Predicate[]::new));
        };
    }

    /**
     * @param searchTerm
     * @return
     */
    private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        } else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }
}
