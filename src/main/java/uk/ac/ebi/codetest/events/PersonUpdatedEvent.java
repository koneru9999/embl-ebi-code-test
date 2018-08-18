package uk.ac.ebi.codetest.events;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;
import uk.ac.ebi.codetest.model.Person;

/**
 * Event object to notify about any updated person(s)
 *
 * @author Venkaiah Chowdary Koneru
 */
@ToString(callSuper = true)
public class PersonUpdatedEvent extends ApplicationEvent {
    @Getter
    private final Person person;

    /**
     * Create a new ApplicationEvent for any updated Person.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public PersonUpdatedEvent(Object source, Person person) {
        super(source);
        this.person = person;
    }
}
