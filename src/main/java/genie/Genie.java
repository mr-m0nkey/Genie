/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import genie.JsonModels.FileImage;
import genie.JsonModels.RootDirectories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
@SpringBootApplication
public class Genie {


    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RootDirectories rootDirectories;


    //TODO  windows filepath (\\) | Mac filepath (/)

    public static FileImageBuilder fileImageBuilder = new FileImageBuilder();
    private static final Logger logger = Logger.getLogger(Genie.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {

        SpringApplication.run(Genie.class, args);



    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RootDirectories getRootDirectories() {
        RootDirectories rootDirectories = null;

        try {
            rootDirectories = initializeRootDirectories();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return rootDirectories;
    }



    private RootDirectories initializeRootDirectories() throws IOException, ExecutionException, InterruptedException {
        String pathToRootDirectoriesJson = System.getProperty("user.dir") + "/root.json";
        File rootJson = new File(pathToRootDirectoriesJson);
        if(rootJson.createNewFile()) {
            mapper.writeValue(rootJson, new RootDirectories());
        }

        RootDirectories rootDirectories = mapper.readValue(rootJson, RootDirectories.class);

        if(rootDirectories.getDirectories() == null) {
            System.out.print("Add a root directory: ");
            Scanner scanner = new Scanner(System.in);
            String newRootPath = scanner.nextLine();
            scanner.close();
            addRootPath(newRootPath, rootDirectories);
            updateRootJsonFile(rootDirectories);
        }

        return rootDirectories;
    }



    private void updateRootJsonFile(RootDirectories rootDirectories) throws IOException {
        String pathToRootDirectoriesJson = System.getProperty("user.dir") + "//root.json";
        File rootJson = new File(pathToRootDirectoriesJson);
        mapper.writerWithDefaultPrettyPrinter().writeValue(rootJson, rootDirectories);
    }

    private void addRootPath(String newRootPath, RootDirectories rootDirectories) throws ExecutionException, InterruptedException {
        Map<String, FileImage> temporaryMap = rootDirectories.getDirectories();
        if(temporaryMap == null) {
            temporaryMap = new HashMap<>();
        }
        temporaryMap.put(newRootPath, fileImageBuilder.build(newRootPath).get());
        rootDirectories.setDirectories(temporaryMap);

    }

}
