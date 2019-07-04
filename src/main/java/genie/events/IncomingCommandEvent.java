package genie.events;

import genie.Command;
import org.springframework.context.ApplicationEvent;

public class IncomingCommandEvent extends ApplicationEvent {

    private Command command;

    public IncomingCommandEvent(Object source, Command command) {
        super(source);
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}