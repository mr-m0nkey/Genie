package genie;

import genie.JsonModels.FileImage;
import genie.JsonModels.IJsonFile;
import genie.JsonModels.JsonDirectory;
import genie.JsonModels.JsonFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class FileImageBuilder {

    public Future<FileImage> build(String pathToRoot) { //TODO use futures to make it concurrent

        Callable<FileImage> callable = () -> {
            FileImage fileImage = new FileImage();

            File rootFolder = new File(pathToRoot);
            JsonDirectory rootJsonDirectory = new JsonDirectory();
            rootJsonDirectory.setPath(rootFolder.getPath());
            rootJsonDirectory.setContent(rootFolder.listFiles());
            rootJsonDirectory.setFile(true);

            fileImage.setRoot(rootJsonDirectory);
            return fileImage;
        };

        Future<FileImage> fileImageFuture = Store.getStore().getExecutor().submit(callable);
        return fileImageFuture;

    }




}
