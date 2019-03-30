/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import genie.JsonModels.FileImage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author mayowa
 */
public class Genie {
    
    public static Store store;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

        String pathToRootDirectoriesJson = System.getProperty("user.dir") + "\\root.json";
        String pathToFileImageJson = System.getProperty("user.dir") + "\\file_image.json";

        FileImageBuilder fileImageBuilder = new FileImageBuilder();
        FileImage fileImage = fileImageBuilder.build("C:\\Users\\mayowa\\Pictures\\iCloud Photos");

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(pathToFileImageJson), fileImage);
    }
    
}
