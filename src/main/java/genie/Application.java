package genie;

import genie.models.json.RootDirectories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class Application implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    FileWatcher fileWatcher;

    @Autowired
    RootDirectories rootDirectories;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        watchRootFilesForChanges();
    }

    public void watchRootFilesForChanges() {
        rootDirectories.getRoots().forEach(pathToRoot -> {
            fileWatcher.getChangesInRootAndPopulateCommandQueue(pathToRoot);
        });
    }





}