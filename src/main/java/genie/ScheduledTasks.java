package genie;

import genie.models.json.FileImage;
import genie.models.json.RootDirectories;
import genie.util.CommandComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.PriorityQueue;
import java.util.Queue;

@Service
public class ScheduledTasks {



    @Autowired
    private Queue<Command> commandsToExecute;


    @Autowired
    RootDirectories rootDirectories;

    @Scheduled(fixedRate = 5000)
    public void syncWithServer() {

        getCommandsFromServer();
        sendCommandsToServer();

    }

    private void getCommandsFromServer() {

    }

    private void sendCommandsToServer() {

    }

    private FileImage scanCurrentFileImage() {

        return new FileImage();
    }

}
