package genie.models.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootDirectories {

    private Map<String, FileImage> directories = new HashMap<>();

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
    }

}
