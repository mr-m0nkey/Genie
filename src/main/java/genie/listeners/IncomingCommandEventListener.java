package genie.listeners;

import genie.events.IncomingCommandEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class IncomingCommandEventListener implements ApplicationListener<IncomingCommandEvent> {


    @Override
    public void onApplicationEvent(IncomingCommandEvent event) {
        event.getCommand().perform();
    }
}