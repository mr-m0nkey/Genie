package genie.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileModel {

    private boolean isDirectory;
    private String name;
    private List<FileModel> content = new ArrayList<>();

    public FileModel(File file) {
        isDirectory = file.isDirectory();
        name = file.getName();
        if (file.isDirectory()) {
            for (File iter : file.listFiles()) {
                addFile(iter);
            }
        }
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FileModel> getContent() {
        return content;
    }

    public void addFile(File file) {
        FileModel fileModel = new FileModel(file);
        content.add(fileModel);
    }


}
