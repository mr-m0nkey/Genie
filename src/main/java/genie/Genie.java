/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

import com.fasterxml.jackson.databind.ObjectMapper;
import genie.models.json.FileImage;
import genie.models.json.RootDirectories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

/**
 *
 * @author mayowa
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Genie {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private FileImageBuilder fileImageBuilder;

    //TODO  windows filepath (\\) | Mac filepath (/)

    private static final Logger logger = Logger.getLogger(Genie.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {

        SpringApplication.run(Genie.class, args);


    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        return executor;
    }


    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RootDirectories getRootDirectories() {
        RootDirectories rootDirectories = new RootDirectories();
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
        //TODO compare old and new roots
        if(rootJson.createNewFile()) {
            mapper.writeValue(rootJson, new RootDirectories());
        }

        RootDirectories rootDirectories = mapper.readValue(rootJson, RootDirectories.class);

        if(rootDirectories.getDirectories() == null) {
            //TODO find another way to handle this rather than using the command line
            System.out.print("Add a root directory: ");
            Scanner scanner = new Scanner(System.in);
            String newRootPath = scanner.nextLine();
            scanner.close();
            addRootToJsonFile(rootDirectories, newRootPath);
        }

        return rootDirectories;
    }

    private void addRootToJsonFile(RootDirectories rootDirectories, String newRootPath) throws ExecutionException, InterruptedException, IOException {
        addRootPath(newRootPath, rootDirectories);
        updateRootJsonFile(rootDirectories);
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
