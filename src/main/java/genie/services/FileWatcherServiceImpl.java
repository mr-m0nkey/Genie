package genie.services;

import genie.interfaces.FileWatcherService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.file.*;

@Service
public class FileWatcherServiceImpl implements FileWatcherService {


    @Async
    public void watchFiles(String filePath) {

        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();

            Path path = Paths.get(filePath);
            path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println(
                            "Event kind:" + event.kind()
                                    + ". File affected: " + event.context());
                }
                key.reset();
            }
        } catch (Exception e) {

        }

    }
}
