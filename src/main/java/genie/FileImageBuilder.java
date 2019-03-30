package genie;

import genie.JsonModels.FileImage;
import genie.JsonModels.IJsonFile;
import genie.JsonModels.JsonDirectory;
import genie.JsonModels.JsonFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileImageBuilder {

    public FileImage build(String pathToRoot) {
        FileImage fileImage = new FileImage();

        File rootFolder = new File(pathToRoot);
        JsonDirectory rootJsonDirectory = new JsonDirectory();
        rootJsonDirectory.setPath(rootFolder.getPath());
        rootJsonDirectory.setContent(rootFolder.listFiles());
        rootJsonDirectory.setFile(true);

        fileImage.setRoot(rootJsonDirectory);
        return fileImage;
    }



}
