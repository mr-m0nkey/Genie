package genie;

import genie.models.json.JsonDirectory;
import genie.models.json.JsonFileRepresentation;
import genie.models.json.RootDirectories;
import net.contentobjects.jnotify.JNotifyListener;
import org.springframework.beans.factory.annotation.Autowired;

class FileListener implements JNotifyListener {

    @Autowired
    RootDirectories rootDirectories;

    public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
        print("renamed " + rootPath + " : " + oldName + " -> " + newName);
    }

    public void fileModified(int wd, String rootPath, String name) {
        print("modified " + rootPath + " : " + name);
    }

    public void fileDeleted(int wd, String rootPath, String name) {
        print("deleted " + rootPath + " : " + name);
    }

    public void fileCreated(int wd, String rootPath, String name) {

        print("created " + rootPath + " : " + name);
//        String[] pathArray = name.split("/");
//
//        JsonFileRepresentation currentFile = null;
//        for(int i = 0; i < pathArray.length; i++) {
//            if(i == pathArray.length - 1) {
//
//            } else {
//                if(currentFile == null) {
//                    JsonFileRepresentation jsonFileRepresentation = rootDirectories.getDirectories().get(rootPath).getRoot().getFile(pathArray[i]);
//                    if(jsonFileRepresentation != null) {
//                        currentFile = jsonFileRepresentation;
//                    }
//                } else {
//                    JsonFileRepresentation jsonFileRepresentation =
//                    if(jsonFileRepresentation != null) {
//                        currentFile = jsonFileRepresentation;
//                    }
//                }
//
//            }
//        }
    }

    void print(String msg) {
        System.err.println(msg);
    }



}
