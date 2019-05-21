package genie;

import genie.models.json.RootDirectories;
import net.contentobjects.jnotify.JNotifyListener;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;

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

        rootDirectories.addFile();
    }

    void print(String msg) {
        System.err.println(msg);
    }

}
