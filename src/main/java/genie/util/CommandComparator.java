package genie.util;

import genie.Command;

import java.util.Comparator;

public class CommandComparator implements Comparator<Command> {

    @Override
    public int compare(Command firstCommand, Command secondCommand) {

        if(firstCommand.getDate().after(secondCommand.getDate())) {
            return 1;
        } else if(firstCommand.getDate().before(secondCommand.getDate())) {
            return -1;
        } else {
            return 0;
        }
    }

}
