package genie.listeners;

import genie.events.IncomingCommandEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class IncomingCommandEventListener implements ApplicationListener<IncomingCommandEvent> {

    @Override
    public void onApplicationEvent(IncomingCommandEvent event) {
        System.out.println("Received spring custom event - ");
        event.getCommand().perform();
    }
}