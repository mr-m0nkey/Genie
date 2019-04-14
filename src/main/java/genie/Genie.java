/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import genie.JsonModels.FileImage;
import genie.JsonModels.RootDirectories;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 *
 * @author mayowa
 */
public class Genie {
    
    public static Store store;
    public static RootDirectories rootDirectories = new RootDirectories();
    public static ObjectMapper mapper = new ObjectMapper();
    public static String pathToFileImageJson = System.getProperty("user.dir") + "\\file_image.json";
    public static FileImageBuilder fileImageBuilder = new FileImageBuilder();
    public static List<FileImage> fileImageList = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Genie.class.getName());
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {

        //TODO handle exceptions
        init();


    }

    private static void init() {
        try {
            initializeRootDirectories();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    private static void initializeRootDirectories() throws IOException, ExecutionException, InterruptedException {
        String pathToRootDirectoriesJson = System.getProperty("user.dir") + "\\root.json";
        File rootJson = new File(pathToRootDirectoriesJson);
        if(rootJson.createNewFile()) {
            mapper.writeValue(rootJson, new RootDirectories());
        }

        rootDirectories = mapper.readValue(rootJson, RootDirectories.class);

        if(rootDirectories.getDirectories() == null) {
            System.out.print("Add a root directory: ");
            Scanner scanner = new Scanner(System.in);
            String newRootPath = scanner.nextLine();
            addRootPath(newRootPath);
            updateRootJsonFile();
        }

    }



    private static void updateRootJsonFile() throws IOException {
        String pathToRootDirectoriesJson = System.getProperty("user.dir") + "\\root.json";
        File rootJson = new File(pathToRootDirectoriesJson);
        mapper.writerWithDefaultPrettyPrinter().writeValue(rootJson, rootDirectories);
    }

    private static void addRootPath(String newRootPath) throws ExecutionException, InterruptedException {
        Map<String, FileImage> temporaryMap = rootDirectories.getDirectories();
        if(temporaryMap == null) {
            temporaryMap = new HashMap<>();
        }
        temporaryMap.put(newRootPath, fileImageBuilder.build(newRootPath).get());
        rootDirectories.setDirectories(temporaryMap);

    }

}
