package uk.ac.ebi.codetest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * @author Venkaiah Chowdary Koneru
 */
@Table(name = "person", indexes = {
        @Index(name = "ix_fname", columnList = "first_name"),
        @Index(name = "ix_lname", columnList = "last_name")})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode
public class Person implements Serializable {
    private static final long serialVersionUID = -7636246558238280505L;

    @Id
    @GeneratedValue
    @Column(name = "person_id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "first_name", length = 150, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 150, nullable = false)
    private String lastName;

    @Column(name = "age", length = 3, nullable = false)
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person", fetch = FetchType.EAGER)
    private Set<PersonExtensions> extensions;

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false, columnDefinition = "timestamp null DEFAULT CURRENT_TIMESTAMP")
    @CreatedDate
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date", columnDefinition = "timestamp null DEFAULT null")
    @LastModifiedDate
    private Date lastModifiedDate;

    @Version
    @Column(name = "version")
    private Integer version;

    @Transient
    private Long createdDateMs;

    @Transient
    private Long lastModifiedDateMs;

    /**
     * @return the created date in millisecond
     */
    public Long getCreatedDateMs() {
        return (createdDate != null) ? new Date(createdDate.getTime()).getTime()
                : 0;
    }

    /**
     * @return the last modified date in millisecond
     */
    public Long getLastModifiedDateMs() {
        return (lastModifiedDate != null)
                ? new Date(lastModifiedDate.getTime()).getTime() : 0;
    }
}
