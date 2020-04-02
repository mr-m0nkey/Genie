package io.macaca.genie.models;

import io.macaca.genie.interfaces.FileWatcherService;
import io.macaca.genie.services.FileWatcherServiceImpl;
import io.macaca.genie.util.DataUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class FileModel {

    private boolean isDirectory;
    private String name;
    private String path;
    private List<FileModel> content = new ArrayList<>();

    public FileModel(File file, FileWatcherServiceImpl fileWatcherService) {


        isDirectory = file.isDirectory();
        path = file.getPath();
        name = file.getName();
        if (file.isDirectory()) {
            for (File iter : file.listFiles()) {
                addFile(iter, fileWatcherService);
            }
        }
        log.info("added: " + file.getAbsolutePath());
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public String getName() {
        return name;
    }

    public String getPath() { return path; }

    public List<FileModel> getContent() {
        return content;
    }

    private void addFile(File file, FileWatcherServiceImpl fileWatcherService) {
        if (!file.isHidden()) {
            FileModel fileModel = new FileModel(file, fileWatcherService);
            if(file.isDirectory()) fileWatcherService.watchFiles(file.getPath());
            DataUtil.addModel(file.getPath(), fileModel);
            content.add(fileModel);
        }
    }

//    TODO move to service and refactor
    public void modifiedHandler(FileWatcherServiceImpl fileWatcherService) {
        Set<String> storedPaths =  this.content.stream().map(FileModel::getPath).collect(Collectors.toSet());
        if(isDirectory) {
            File file = new File(path);
            for(File childFile : file.listFiles()) {
                String childPath = childFile.getPath();
                if(!storedPaths.contains(childPath) && !childFile.isHidden()) {
                    FileModel fileModel = new FileModel(childFile, fileWatcherService);
                    content.add(fileModel);
                    DataUtil.addModel(childPath, fileModel);
                    if(childFile.isDirectory()){
                        //TODO add to database for job to pick
                    }
                }
            }

            storedPaths.stream().forEach(path -> {
                List<String> actualPaths = Arrays.asList(file.listFiles()).stream().map(File::getPath).collect(Collectors.toList());
                if(!actualPaths.contains(path)) {
                    content.remove(DataUtil.map.get(path));
                    DataUtil.map.remove(path);
                    log.info("removed: " + path);
                    DataUtil.map.keySet().stream().forEach(key -> {
                        if(key.startsWith(path)) {
                            DataUtil.map.remove(key);
                            log.info("removed: " + key);
                        }
                    });
                }
            });

        }
    }


    public void watch(FileWatcherServiceImpl fileWatcherService) {
        if(isDirectory) fileWatcherService.watchFiles(path);
    }
}
