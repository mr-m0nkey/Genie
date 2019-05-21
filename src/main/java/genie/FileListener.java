package genie;

import genie.models.json.RootDirectories;
import net.contentobjects.jnotify.JNotifyListener;
import org.springframework.beans.factory.annotation.Autowired;

class FileListener implements JNotifyListener {

    @Autowired
    RootDirectories rootDirectories;

    public void fileRenamed(int wd, String rootPath, String oldName,
                            String newName) {
        print("renamed " + rootPath + " : " + oldName + " -> " + newName);
        crawl(rootPath);

    }

    public void fileModified(int wd, String rootPath, String name) {
        print("modified " + rootPath + " : " + name);
    }

    public void fileDeleted(int wd, String rootPath, String name) {
        print("deleted " + rootPath + " : " + name);
    }

    public void fileCreated(int wd, String rootPath, String name) {
        print("created " + rootPath + " : " + name);
    }

    void print(String msg) {
        System.err.println(msg);
    }


    void crawl(String path) {
        String[] pathArray = path.split("/");
        String rootPath = "";
        for(int i = 0; i < pathArray.length; i++) {
            rootPath += pathArray[i];
            if(rootDirectories.getRoots().contains(rootPath)) {
                System.out.println(rootPath);
            }
        }
    }

}
