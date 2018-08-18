package uk.ac.ebi.codetest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Base audit entity class which can be extended by any entity class
 *
 * @author Venkaiah Chowdary Koneru
 */
@Data
@EqualsAndHashCode
@MappedSuperclass
public abstract class AuditEntity implements Serializable {

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
