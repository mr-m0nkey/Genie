/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie.models.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Stores a mirror of the file system
 * @author mayowa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileImage {

    private JsonFile root;

    public JsonFile getRoot() {
        return root;
    }

    public void setRoot(JsonFile root) {
        this.root = root;
    }

}