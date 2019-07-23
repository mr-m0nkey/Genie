package genie.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import genie.models.json.RootDirectories;
import genie.services.FilesystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Configuration
public class FileSystemConfig {


    @Autowired
    ObjectMapper mapper;
    @Autowired
    private FilesystemService filesystemService;

    @Bean
    public RootDirectories getRootDirectories() {
        RootDirectories rootDirectories = new RootDirectories();
        try {
            rootDirectories = filesystemService.initializeRootDirectories();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return rootDirectories;
    }


}
