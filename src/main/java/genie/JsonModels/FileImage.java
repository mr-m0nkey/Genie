/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie.JsonModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.File;

/**
 * Stores a mirror of the file system
 * @author mayowa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileImage {

    private JsonDirectory root;

    public JsonDirectory getRoot() {
        return root;
    }

    public void setRoot(JsonDirectory root) {
        this.root = root;
    }

}
