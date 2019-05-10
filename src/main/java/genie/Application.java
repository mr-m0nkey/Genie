package genie;

import genie.models.json.RootDirectories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Component
public class Application implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    FileWatcher fileWatcher;

    @Autowired
    RootDirectories rootDirectories;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        getChangesInRoots();
    }

    public void getChangesInRoots() {
        rootDirectories.getRoots().forEach(pathToRoot -> {
            fileWatcher.getChangesInRoot(pathToRoot);
        });
    }





}