package genie;

import genie.models.json.FileImage;
import genie.models.json.RootDirectories;
import genie.publishers.IncomingCommandEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasks {


    @Autowired
    IncomingCommandEventPublisher incomingCommandEventPublisher;

    @Autowired
    private OutgoingCommands outgoingCommands;

    @Autowired
    private IncomingCommands incomingCommands;


    @Autowired
    RootDirectories rootDirectories;

    @Scheduled(fixedRate = 10000)
    public void syncWithServer() {
        getCommandsFromServer();
        sendCommandsToServer();
        executeCommands();
    }

    @Async
    private void executeCommands() {
        incomingCommands.getStream().forEach(command -> {
            incomingCommandEventPublisher.publish(command);
        });
    }

    private void getCommandsFromServer() {

    }

    private void sendCommandsToServer() {

    }

    private FileImage scanCurrentFileImage() {

        return new FileImage();
    }

}
