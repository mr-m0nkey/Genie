/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import genie.JsonModels.FileImage;
import genie.JsonModels.RootDirectories;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author mayowa
 */
public class Genie {
    
    public static Store store;
    public static RootDirectories rootDirectories;
    public static ObjectMapper mapper = new ObjectMapper();
    public static String pathToFileImageJson = System.getProperty("user.dir") + "\\file_image.json";
    public static FileImageBuilder fileImageBuilder = new FileImageBuilder();
    public static List<FileImage> fileImageList = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {

        //TODO handle exceptions
        try {
            initializeRootDirectories();
            initializeFileImages();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }



    private static void initializeRootDirectories() throws IOException {
        String pathToRootDirectoriesJson = System.getProperty("user.dir") + "\\root.json";
        File rootJson = new File(pathToRootDirectoriesJson);
        if(rootJson.createNewFile()) {
            mapper.writeValue(rootJson, new RootDirectories());
        } else {
            rootDirectories = mapper.readValue(rootJson, RootDirectories.class);
        }
    }

    private static void initializeFileImages() throws ExecutionException, InterruptedException {
        String[] pathsToRootDirectories = rootDirectories.getDirectories();
        if(pathsToRootDirectories != null) {
            List<Future<FileImage>> futureList = new ArrayList<>();
            for(String rootDirectory : pathsToRootDirectories) {
                futureList.add(fileImageBuilder.build(rootDirectory));
            }
            for(Future<FileImage> future : futureList) {
                fileImageList.add(future.get());
            }
        } else {
            //TODO prompt the user to set a root directory
            System.out.println("Set a root directory");
        }
    }

}
