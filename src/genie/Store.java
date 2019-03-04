/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author mayowa
 */
public class Store {
    
    private ArrayList<String> rootDirectories;
    private static Store store;
    private ExecutorService executorService;
    
    private Store() {
        rootDirectories = new ArrayList();
        executorService = Executors.newSingleThreadExecutor();
    }
    
    public static Store getStore() {
        if(store == null) {
            store = new Store();
            
        }
        return store;
    }
    
    public boolean addRoot(String pathToRoot) {
        pathToRoot = pathToRoot.toLowerCase();
        if(rootDirectories.contains(pathToRoot)) {
            return false;
        }
        rootDirectories.add(pathToRoot);
        return true;
    }
    
    public ExecutorService getExecutor() {
        return executorService;
    }
    
}
