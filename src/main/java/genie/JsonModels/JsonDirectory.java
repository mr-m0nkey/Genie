package genie.JsonModels;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonDirectory extends JsonFileRepresentation implements IJsonFile{

    List<IJsonFile> content;

    public List<IJsonFile> getContent() {
        return content;
    }

    public void setContent(File[] directory) {
        List<IJsonFile> content = new ArrayList<>();
        for(File file : directory) {
            IJsonFile jsonFile;
            if(file.isFile()) {
                jsonFile = getiJsonFile(file);
            } else {
                jsonFile = getiJsonDirectory(file);
            }
            content.add(jsonFile);
        }
        this.content = content;
    }

    private IJsonFile getiJsonDirectory(File file) {
        IJsonFile jsonFile;
        jsonFile = new JsonDirectory();
        ((JsonDirectory) jsonFile).setName(file.getName());
        ((JsonDirectory) jsonFile).setPath(file.getPath());
        ((JsonDirectory) jsonFile).setContent(file.listFiles());
        ((JsonDirectory) jsonFile).setFile(false);
        ((JsonDirectory) jsonFile).setLastModified(file.lastModified());
        return jsonFile;
    }

    private IJsonFile getiJsonFile(File file) {
        IJsonFile jsonFile;
        jsonFile = new JsonFile();
        ((JsonFile) jsonFile).setPath(file.getPath());
        String directoryName = getNameFromPath(file.getPath());
        ((JsonFile) jsonFile).setName(directoryName);
        ((JsonFile) jsonFile).setFile(true);
        ((JsonFile) jsonFile).setLastModified(file.lastModified());
        return jsonFile;
    }

    private String getNameFromPath(String path) {
        String name = "";
        String[] pathArray = path.split("/");
        name = pathArray[pathArray.length - 1];
        return name;
    }

}
