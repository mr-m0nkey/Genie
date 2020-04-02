package io.macaca.genie.services;

import io.macaca.genie.interfaces.FileSystemService;
import io.macaca.genie.interfaces.FileWatcherService;
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
public class FileWatcherServiceImpl implements FileWatcherService {

    private static Map<WatchService, String> watcherMap = new HashMap<>();

    @Autowired
    private FileSystemService fileSystemService;

    @Async
    public void watchFiles(String filePath) {

        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            watcherMap.put(watchService, filePath);

            Path path = Paths.get(filePath);
            path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    handleEvent(event, watchService);
                }
                key.reset();
            }
        } catch (Exception e) {

        }

    }

    private void handleEvent(WatchEvent<?> event, WatchService watchService) {
        String absolutePath = watcherMap.get(watchService);
        try {
            String root  = fileSystemService.getRootFromPath(absolutePath);
            log.info("Event kind:" + event.kind() + ", File affected: " + event.context());
            if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                handleCreateEvent(absolutePath, root);
            } else if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
                handleModifyEvent(absolutePath, root);
            } else {
                handleDeleteEvent(absolutePath, root);
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }

    }



    /*
    TODO create command to be uploaded to the server
    modify filesystem
     */


    private void handleCreateEvent(String path, String root) {

    }

    private void handleModifyEvent(String path, String root) {

    }

    private void handleDeleteEvent(String path, String root) {

    }
}
