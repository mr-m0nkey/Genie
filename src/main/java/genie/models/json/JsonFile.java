package genie.models.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonFile {

    private String path;
    private String name;
    private boolean isFile;
    private long lastModified;
    private List<JsonFile> content;

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

    public List<JsonFile> getContent() {
        return content;
    }

    public void setContent(List<JsonFile> content) {
        this.content = content;
    }

    @JsonIgnore
    public JsonFile getFile(String name) {

        for(JsonFile file : content) {
            if(file.getName().equals(name)) {
                return file;
            }
        }

        return null;
    }

    @JsonIgnore
    public void addFile(JsonFile file) {
        content.add(file);
    }

}
