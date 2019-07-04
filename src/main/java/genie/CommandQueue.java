package genie;

import genie.util.CommandComparator;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;

public abstract class CommandQueue {

    private Queue<Command> commandsToExecute = new PriorityQueue(new CommandComparator());

    public void pushAndSave(Command command) {
        commandsToExecute.add(command);
    }

    public Command popAndDelete() {
        return commandsToExecute.remove();
        //TODO remove from storage
    }

    public Command peek() {
        return commandsToExecute.peek();
    }

    public boolean isEmpty() {
        return commandsToExecute.isEmpty();
    }

    public Stream<Command> getStream() {
        return commandsToExecute.stream();
    }
}
