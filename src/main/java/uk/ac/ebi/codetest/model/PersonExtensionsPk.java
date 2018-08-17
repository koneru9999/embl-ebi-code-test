package uk.ac.ebi.codetest.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Venkaiah Chowdary Koneru
 */
@Embeddable
@Data
public class PersonExtensionsPk implements Serializable {
    private static final long serialVersionUID = -2026020609641626311L;

    @Column(name = "person_id", columnDefinition = "uuid", nullable = false)
    private UUID personId;

    @Column(name = "extension_key", length = 100, nullable = false)
    private String extensionKey;
}
