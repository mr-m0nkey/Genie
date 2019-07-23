package genie.publishers;

import genie.Command;
import genie.events.IncomingCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class IncomingCommandEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(final Command command) {
        IncomingCommandEvent incomingCommandEvent = new IncomingCommandEvent(this, command);
        applicationEventPublisher.publishEvent(incomingCommandEvent);
    }
}