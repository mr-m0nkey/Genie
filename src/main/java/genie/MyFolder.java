/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

import Interfaces.IJSON;
import java.io.File;

/**
 *
 * @author mayowa
 */
public class MyFolder extends File implements IJSON {

    public MyFolder(String pathname) {
        super(pathname);
    }

    @Override
    public String toJson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
