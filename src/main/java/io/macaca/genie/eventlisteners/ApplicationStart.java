package io.macaca.genie.eventlisteners;

import io.macaca.genie.interfaces.FileSystemService;
import io.macaca.genie.models.FileModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ApplicationStart implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private FileSystemService fileSystemService;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Application starting...");
        Map<String, FileModel> filemodels = new HashMap<>();
        for(String root : fileSystemService.getRootDirectories()) {

            File baseDirectory = new File(root);
            FileModel fileSystem = fileSystemService.getFileSystem(baseDirectory);
            filemodels.put(root, fileSystem);
        }
//        TODO
//                        get filesystem from server
//                compare filesystems and edit differences

    }

}
