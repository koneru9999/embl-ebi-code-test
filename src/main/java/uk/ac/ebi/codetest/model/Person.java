package uk.ac.ebi.codetest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Venkaiah Chowdary Koneru
 */
@Table(name = "person", indexes = {
        @Index(name = "ix_fname", columnList = "first_name"),
        @Index(name = "ix_lname", columnList = "last_name"),
        @Index(name = "ix_fav_colour", columnList = "favourite_colour")
})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Person extends AuditEntity {
    private static final long serialVersionUID = -7636246558238280505L;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "PERSON_ID_SEQ_GENERATOR")
    @SequenceGenerator(
            name = "PERSON_ID_SEQ_GENERATOR",
            sequenceName = "PERSON_ID_SEQ",
            allocationSize = 1
    )
    @Column(name = "person_id", updatable = false)
    private Long id;

    @Column(name = "first_name", length = 150, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 150, nullable = false)
    private String lastName;

    @Column(name = "age", length = 3, nullable = false)
    private String age;

    @Column(name = "favourite_colour", nullable = false)
    private String favouriteColour;
}
