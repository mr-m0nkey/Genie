/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Queue;

/**
 * Queues commands and handles execution,
 * TODO run on a separate thread
 * @author mayowa
 */
@Service
public class Tunnel {

    Queue<Command> commands;
    
    /**
     *
     * @param command
     * @return
     */
    public boolean add(Command command) {
        commands.add(command);
        return true;
    }
    
    /**
     *
     */
    @Async
    public void loop() {
        while(!commands.isEmpty()) {
            if(commands.peek().perform()) {
                commands.remove();
            }
        }
    }
}
