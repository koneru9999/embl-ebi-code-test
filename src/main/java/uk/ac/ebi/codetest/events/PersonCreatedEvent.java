package uk.ac.ebi.codetest.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;
import uk.ac.ebi.codetest.model.Person;

/**
 * Event object to notify about any new person(s) created
 *
 * @author Venkaiah Chowdary Koneru
 */
@ToString(callSuper = true)
public class PersonCreatedEvent extends ApplicationEvent {
    @Getter
    private final Person person;

    /**
     * Create a new ApplicationEvent for new Person.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public PersonCreatedEvent(Object source, Person person) {
        super(source);
        this.person = person;
    }
}
