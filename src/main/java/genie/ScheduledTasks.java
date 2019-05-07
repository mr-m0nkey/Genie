package genie;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 5000)
    public void syncWithServer() {

    }

    private void getCommandsFromServer() {

    }

    private void sendCommandsToServer() {

    }

    private void checkFileImageForChanges() {

    }


}
