package genie;

import net.contentobjects.jnotify.JNotifyListener;

class FileListener implements JNotifyListener {
    public void fileRenamed(int wd, String rootPath, String oldName,
                            String newName) {
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
    }

    void print(String msg) {
        System.err.println(msg);
    }

}
