/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

import Interfaces.IJSON;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Stores a mirror of the file system
 * @author mayowa
 */
public class FileImage implements IJSON {
    
    private File root;
    
    private FileImage(String pathToRoot) {
        this.root = new File(pathToRoot);
        //TODO create fileimage
    }
    
    public static Future<FileImage> make(String pathToRoot) {
        
        Callable<FileImage> callable = () -> {
            return new FileImage(pathToRoot);
        };
        Future<FileImage> future = Store.getExecutor().submit(callable);
        return future; 
    }

    @Override
    public String toJson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
