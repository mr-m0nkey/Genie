package genie.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import genie.FileImageBuilder;
import genie.exceptions.HiddenFileNotFoundException;
import genie.models.json.FileImage;
import genie.models.json.RootDirectories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FilesystemService {

    //TODO use properties file
    public final String hiddenFileName = ".genie";
    //TODO use properties file
    public final String jsonFileName = ".root.json";
    public final String currentFileSystem = System.getProperty("user.dir");
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private FileImageBuilder fileImageBuilder;

    public void updateRootJsonFile(RootDirectories rootDirectories) throws IOException {
        String pathToRootDirectoriesJson = System.getProperty("user.dir") + "/" + jsonFileName;
        File rootJson = new File(pathToRootDirectoriesJson);
        mapper.writerWithDefaultPrettyPrinter().writeValue(rootJson, rootDirectories);
    }

    public void addRootToJsonFile(RootDirectories rootDirectories, String newRootPath) throws ExecutionException, InterruptedException, IOException {
        addRootPath(newRootPath, rootDirectories);
        updateRootJsonFile(rootDirectories);
    }


    public void addRootPath(String newRootPath, RootDirectories rootDirectories) throws ExecutionException, InterruptedException {
        Map<String, FileImage> temporaryMap = rootDirectories.getDirectories();
        if (temporaryMap == null) {
            temporaryMap = new HashMap<>();
        }
        temporaryMap.put(newRootPath, fileImageBuilder.build(newRootPath).get());
        rootDirectories.setDirectories(temporaryMap);
        saveHiddenFileInRoot(newRootPath);
    }

    public void saveHiddenFileInRoot(String newRootPath) {

        File file = new File(newRootPath + "/" + hiddenFileName);
        try {
            file.createNewFile();
            if (SystemService.OS.contains("Windows")) {
                Path path = Paths.get(newRootPath);
                Files.setAttribute(path, "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public RootDirectories initializeRootDirectories() throws IOException, ExecutionException, InterruptedException {
        String pathToRootDirectoriesJson = currentFileSystem + "/root.json";
        File rootJson = new File(pathToRootDirectoriesJson);
        //TODO compare old and new roots
        if (rootJson.createNewFile()) {
            mapper.writeValue(rootJson, new RootDirectories());
        }
        RootDirectories rootDirectories = mapper.readValue(rootJson, RootDirectories.class);
        for (String rootPath : rootDirectories.getDirectories().keySet()) {
            try {
                checkForHiddenFile(rootPath);
            } catch (HiddenFileNotFoundException e) {
                //TODO log
                //TODO do something about the folder
            }
        }
        return rootDirectories;
    }

    private void checkForHiddenFile(String rootPath) throws HiddenFileNotFoundException {
        File hiddenFile = new File(rootPath + "/" + hiddenFileName);
        if (!hiddenFile.exists()) {
            throw new HiddenFileNotFoundException();
        }
    }


}
