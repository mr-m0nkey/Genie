/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

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
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // TODO code application logic here
        store = Store.getStore();
        FileImage fileImage = FileImage.make("").get();
    }
    
}
