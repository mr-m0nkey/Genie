package genie.models.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootDirectories {

    private Map<String, FileImage> directories;

    public Map<String, FileImage> getDirectories() {
        return directories;
    }

    public void setDirectories(Map<String, FileImage> directories) {
        this.directories = directories;
    }

    @JsonIgnore
    public Set<String> getRoots() {
        return directories.keySet();
    }

    @JsonIgnore
    public void addFile() {
       System.out.println("Adding file to root directory");
        //System.out.println(file.getPath() + " : " + file.getName());
    }

}
