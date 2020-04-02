package io.macaca.genie.services;

import io.macaca.genie.interfaces.FileSystemService;
import io.macaca.genie.interfaces.FileWatcherService;
import io.macaca.genie.models.FileModel;
import io.macaca.genie.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class FileWatcherServiceImpl{

    @Autowired
    private FileSystemService fileSystemService;

    @Async("threadPoolTaskExecutor")
    public void watchFiles(String filePath) {

        try {
            log.info("Watching: " + filePath);
            WatchService watchService = FileSystems.getDefault().newWatchService();
            DataUtil.watcherMap.put(filePath, watchService);

            Path path = Paths.get(filePath);
            path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.OVERFLOW);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    handleEvent(event, watchService, filePath);
                }
                key.reset();
            }
        } catch (ClosedWatchServiceException e) {
            log.info("Watch service has been closed for " + filePath);
        } catch(Exception e) {
            log.error("Watcher error", e);
        }

        log.warn("Done");


    }

    private void handleEvent(WatchEvent<?> event, WatchService watchService, String file) {
        if(event.context().toString().startsWith(".")) {
            return;
        }
        log.info("Event kind:" + event.kind() + ", File affected: " + event.context() + ", root: " + file);
        try {
            String root  = fileSystemService.getRootFromPath(file);

            //TODO refactor
            if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {

                DataUtil.map.get(file).modifiedHandler(this);
                String aaa = file + "/" + event.context().toString();
                DataUtil.map.get(aaa).modifiedHandler(this);

            } else if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)) {

                String aaa = file + "/" + event.context().toString();
                DataUtil.map.get(aaa).modifiedHandler(this);
                DataUtil.map.get(aaa).modifiedHandler(this);


            } else if (event.kind().equals(StandardWatchEventKinds.ENTRY_DELETE)) {
                DataUtil.map.get(file).modifiedHandler(this);
                String aaa = file + "/" + event.context().toString();

            } else {
                log.warn("Overflow");
            }

        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }

    }



    /*
    TODO create command to be uploaded to the server
    modify filesystem
     */


    private void handleCreateEvent(String path, String root, String newFile) {

    }

    private void handleModifyEvent(String path, String root) {

    }

    private void handleDeleteEvent(String path, String root) {

    }
}
