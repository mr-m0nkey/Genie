package genie;

import genie.models.json.RootDirectories;
import net.contentobjects.jnotify.JNotifyListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Queue;

class FileListener implements JNotifyListener {

    @Autowired
    RootDirectories rootDirectories;

    @Autowired
    Queue<Command> commandQueue;

    @Autowired
    Tunnel tunnel;


    public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
        print("renamed " + rootPath + " : " + oldName + " -> " + newName);

        Command command = new Command();

        command.setCommandType(Command.COMMAND_TYPE.RENAMED);
        command.setDate(new Date());
        command.setRootDirectory(rootPath);

        String oldFilePath = getFilePath(rootPath, oldName);
        command.setFormerFilePath(oldFilePath);

        String newFilePath = getFilePath(rootPath, newName);
        command.setFilePath(newFilePath);

        commandQueue.add(command);

    }

    public void fileModified(int wd, String rootPath, String name) {

        print("modified " + rootPath + " : " + name);

        Command command = new Command();

        command.setCommandType(Command.COMMAND_TYPE.MODIFIED);
        command.setDate(new Date());
        command.setRootDirectory(rootPath);

        String filePath = getFilePath(rootPath, name);
        command.setFilePath(filePath);

        commandQueue.add(command);


    }

    public void fileDeleted(int wd, String rootPath, String name) {

        print("deleted " + rootPath + " : " + name);

        Command command = new Command();

        command.setCommandType(Command.COMMAND_TYPE.DELETED);
        command.setDate(new Date());
        command.setRootDirectory(rootPath);

        String filePath = getFilePath(rootPath, name);
        command.setFilePath(filePath);

        commandQueue.add(command);
    }

    public void fileCreated(int wd, String rootPath, String name) {

        print("created " + rootPath + " : " + name);

        Command command = new Command();

        command.setCommandType(Command.COMMAND_TYPE.CREATED);
        command.setDate(new Date());
        command.setRootDirectory(rootPath);

        String filePath = getFilePath(rootPath, name);
        command.setFilePath(filePath);

        commandQueue.add(command);

    }

    void print(String msg) {
        System.err.println(msg);
    }

    private String getFilePath(String rootPath, String name) {

        return "";
    }



}
