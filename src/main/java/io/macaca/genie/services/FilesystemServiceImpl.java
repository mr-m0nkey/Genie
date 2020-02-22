package io.macaca.genie.services;

import io.macaca.genie.exceptions.NotADirectoryException;
import io.macaca.genie.interfaces.FileSystemService;
import io.macaca.genie.interfaces.FileWatcherService;
import io.macaca.genie.models.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FilesystemServiceImpl implements FileSystemService {

    @Autowired
    private FileWatcherService fileWatcherService;

    @Override
    public FileModel getFileSystem(File directory) {
        if (!directory.isDirectory()) {
            throw new NotADirectoryException();
        }
        FileModel fileModel = buildFilesystem(directory);
        return fileModel;

    }


    private FileModel buildFilesystem(File file) {
        FileModel fileModel = new FileModel(file, fileWatcherService);
        return fileModel;
    }

}
