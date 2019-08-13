package genie.services;

import genie.interfaces.FileWatcherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class FileWatcherServiceImpl implements FileWatcherService {

    private static Map<WatchService, String> watcherMap = new HashMap<>();

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
        log.info("Event kind:" + event.kind() + ", File affected: " + event.context());
        if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
            handleCreateEvent(absolutePath);
        } else if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
            handleModifyEvent(absolutePath);
        } else {
            handleDeleteEvent(absolutePath);
        }
    }

    /*
    TODO create command to be uploaded to the server
    modify filesystem
     */


    private void handleCreateEvent(String path) {

    }

    private void handleModifyEvent(String path) {

    }

    private void handleDeleteEvent(String path) {

    }
}
