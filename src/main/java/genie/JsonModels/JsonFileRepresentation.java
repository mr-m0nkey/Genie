package genie.JsonModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//TODO make abstract
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonFileRepresentation implements IJsonFile {

    private String path;
    private String name;
    private boolean isFile;
    private long lastModified;


    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public String getPath() {

        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
