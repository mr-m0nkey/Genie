package genie.models;

import genie.interfaces.FileWatcherService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileModel {

    private boolean isDirectory;
    private String name;
    private List<FileModel> content = new ArrayList<>();

    public FileModel(File file, FileWatcherService fileWatcherService) {
        fileWatcherService.watchFiles(file.getAbsolutePath());
        isDirectory = file.isDirectory();
        name = file.getName();
        if (file.isDirectory()) {
            for (File iter : file.listFiles()) {
                addFile(iter, fileWatcherService);
            }
        }
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public String getName() {
        return name;
    }

    public List<FileModel> getContent() {
        return content;
    }

    public void addFile(File file, FileWatcherService fileWatcherService) {
        if (!file.isHidden()) {
            FileModel fileModel = new FileModel(file, fileWatcherService);
            content.add(fileModel);
        }
    }

}
