package genie;

import genie.util.CommandComparator;
import org.springframework.stereotype.Service;

import java.util.PriorityQueue;
import java.util.Queue;

@Service
public class CommandQueue {

    private Queue<Command> commandsToExecute = new PriorityQueue(new CommandComparator());
}
