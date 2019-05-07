package genie.JsonModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootDirectories {

    private Map<String, FileImage> directories;

    public Map<String, FileImage> getDirectories() {
        return directories;
    }

    public void setDirectories(Map<String, FileImage> directories) {
        this.directories = directories;
    }

}
