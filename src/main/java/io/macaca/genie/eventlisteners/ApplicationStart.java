package io.macaca.genie.eventlisteners;

import io.macaca.genie.interfaces.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStart implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private FileSystemService fileSystemService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        TODO get base directories
//                build filesystem
//                        get filesystem from server
//                compare filesystems and edit differences

    }

}
