package uk.ac.ebi.codetest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity class for person Extensions.
 * <p>
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@EqualsAndHashCode(exclude = "person")
@ToString(exclude = "person")
@Entity
@Table(name = "person_extensions")
public class PersonExtensions implements Serializable {
    private static final long serialVersionUID = -7333195234339364201L;

    @EmbeddedId
    private PersonExtensionsPk id;

    @ManyToOne
    @JoinColumn(name = "person_id",
            referencedColumnName = "person_id", insertable = false, updatable = false)
    private Person person;

    @Column(name = "extension_value")
    private String extensionValue;

    /**
     * @return
     */
    public String getExtensionKey() {
        return id == null ? null : id.getExtensionKey();
    }
}
