package uk.ac.ebi.codetest.service.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import uk.ac.ebi.codetest.events.PersonCreatedEvent;

/**
 * person creation event listener.
 *
 * @author Venkaiah Chowdary Koneru
 */
@Component
@Slf4j
public class PersonCreatedEventListener implements ApplicationListener<PersonCreatedEvent> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onApplicationEvent(PersonCreatedEvent event) {
        log.info("A new person event is received {}", event);
    }
}
