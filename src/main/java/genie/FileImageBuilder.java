package genie;

import genie.models.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Service
public class FileImageBuilder {

    @Autowired
    Store store;

    public Future<FileImage> build(String pathToRoot) {

        Callable<FileImage> callable = () -> {
            FileImage fileImage = new FileImage();

            File rootFolder = new File(pathToRoot);
            JsonDirectory rootJsonDirectory = new JsonDirectory();
            rootJsonDirectory.setPath(rootFolder.getPath());
            rootJsonDirectory.setContent(setContent(rootFolder.listFiles()));
            rootJsonDirectory.setFile(true);
            fileImage.setRoot(rootJsonDirectory);
            return fileImage;
        };

        Future<FileImage> fileImageFuture = store.getTunnelThread().submit(callable);
        return fileImageFuture;

    }



    public List<JsonFileRepresentation> setContent(File[] directory) {
        List<JsonFileRepresentation> content = new ArrayList<>();
        for(File file : directory) {
            JsonFileRepresentation jsonFile;
            if(file.isFile()) {
                jsonFile = getiJsonFile(file.getPath());
            } else {
                jsonFile = getiJsonDirectory(file.getPath());
            }
            content.add(jsonFile);
        }
        return content;
    }

    private JsonFileRepresentation getiJsonDirectory(String filePath) {
        File file = new File(filePath);
        JsonFileRepresentation jsonFile;
        jsonFile = new JsonDirectory();
        jsonFile.setName(file.getName());
        jsonFile.setPath(file.getPath());
        ((JsonDirectory) jsonFile).setContent(setContent(file.listFiles()));
        jsonFile.setFile(false);
        jsonFile.setLastModified(file.lastModified());
        return jsonFile;
    }

    private JsonFileRepresentation getiJsonFile(String filePath) {
        File file = new File(filePath);
        JsonFileRepresentation jsonFile;
        jsonFile = new JsonFile();
        jsonFile.setPath(file.getPath());
        String directoryName = getNameFromPath(file.getPath());
        jsonFile.setName(directoryName);
        jsonFile.setFile(true);
        jsonFile.setLastModified(file.lastModified());
        return jsonFile;
    }

    private String getNameFromPath(String path) {
        String name = "";
        String[] pathArray = path.split("/");
        name = pathArray[pathArray.length - 1];
        return name;
    }




}
