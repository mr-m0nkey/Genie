package io.macaca.genie.services;

import io.macaca.genie.exceptions.NotADirectoryException;
import io.macaca.genie.exceptions.RootAlreadyExistsException;
import io.macaca.genie.interfaces.FileSystemService;
import io.macaca.genie.interfaces.FileWatcherService;
import io.macaca.genie.models.FileModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FilesystemServiceImpl implements FileSystemService {

    @Autowired
    private FileWatcherService fileWatcherService;

    @Override
    public List<String> getRootDirectories() {
        List<String> directories = new ArrayList<>();
        directories.add("/Users/majagunna/dumps");
        return directories;
    }

    @Override
    public FileModel getFileSystem(File directory) {
        if (!directory.isDirectory()) throw new NotADirectoryException();
        log.info("Getting filesystem for " + directory);
        FileModel fileModel = buildFilesystem(directory);
        return fileModel;
    }


    private FileModel buildFilesystem(File file) {
        FileModel fileModel = new FileModel(file, fileWatcherService);
        return fileModel;
    }

    public void addRootDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if(!directory.isDirectory()) throw new NotADirectoryException();
        for(String rootDirectory : getRootDirectories()) {
            if(directoryPath.startsWith(rootDirectory)) throw new RootAlreadyExistsException();
        }
    }

    public String getRootFromPath(String absolutePath) throws FileNotFoundException {
        for(String directory : getRootDirectories()) {
            if(absolutePath.startsWith(directory)) return directory;
        }
        throw new FileNotFoundException(absolutePath + "not found in root directories");
    }

}
