package io.macaca.genie.services;

import io.macaca.genie.entities.RootDirectory;
import io.macaca.genie.exceptions.NotADirectoryException;
import io.macaca.genie.exceptions.RootAlreadyExistsException;
import io.macaca.genie.interfaces.FileSystemService;
import io.macaca.genie.interfaces.FileWatcherService;
import io.macaca.genie.models.FileModel;
import io.macaca.genie.repository.RootDirectoryRepository;
import io.macaca.genie.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilesystemServiceImpl implements FileSystemService {


    @Autowired
    private FileWatcherServiceImpl fileWatcherService;

    @Autowired
    private RootDirectoryRepository rootDiectoryRepository;

    @Override
    public List<RootDirectory> getRootDirectories() {
        return rootDiectoryRepository.findAll();
    }

    public List<String> getRootPaths() {
        return getRootDirectories()
                .stream()
                .map(RootDirectory::getPath)
                .collect(Collectors.toList());
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
        fileWatcherService.watchFiles(file.getPath());
        DataUtil.addModel(file.getPath(), fileModel);
        return fileModel;
    }

    public void addRootDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if(!directory.isDirectory()) throw new NotADirectoryException();
        List<String> rootDirectories = getRootDirectories().stream().map(RootDirectory::getPath).collect(Collectors.toList());
        for(String rootDirectory : rootDirectories) {
            if(directoryPath.startsWith(rootDirectory)) throw new RootAlreadyExistsException();
        }
        rootDirectories.add(directoryPath);
    }

    public String getRootFromPath(String absolutePath) throws FileNotFoundException {
        List<String> rootDirectories = getRootDirectories().stream().map(RootDirectory::getPath).collect(Collectors.toList());
        for(String directory : rootDirectories) {
            if(absolutePath.startsWith(directory)) return directory;
        }
        throw new FileNotFoundException(absolutePath + "not found in root directories");
    }

}
