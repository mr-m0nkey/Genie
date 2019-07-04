package genie;

import genie.enums.COMMAND_TYPE;
import genie.models.json.RootDirectories;
import net.contentobjects.jnotify.JNotifyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

class FileListener implements JNotifyListener {

    @Autowired
    RootDirectories rootDirectories;

    private static final Logger logger = LoggerFactory.getLogger(FileListener.class);

    @Autowired
    Tunnel tunnel;
    @Autowired
    OutgoingCommands outgoingCommands;

    public void fileRenamed(int wd, String rootPath, String oldName, String newName) {

        String logMessage = String.format("renamed %s : %s -> %s", rootPath, oldName, newName);
        logger.info(logMessage);

        Command command = new Command();

        command.setCommandType(COMMAND_TYPE.RENAMED);
        command.setDate(new Date());
        command.setRootDirectory(rootPath);

        String oldFilePath = getFilePath(rootPath, oldName);
        command.setFormerFilePath(oldFilePath);

        String newFilePath = getFilePath(rootPath, newName);
        command.setFilePath(newFilePath);

        outgoingCommands.pushAndSave(command);

    }

    public void fileModified(int wd, String rootPath, String name) {
        String logMessage = String.format("modified %s : %s", rootPath, name);

        logger.info(logMessage);

        Command command = new Command();

        command.setCommandType(COMMAND_TYPE.MODIFIED);
        command.setDate(new Date());
        command.setRootDirectory(rootPath);

        String filePath = getFilePath(rootPath, name);
        command.setFilePath(filePath);

        outgoingCommands.pushAndSave(command);


    }

    public void fileDeleted(int wd, String rootPath, String name) {

        String logMessage = String.format("deleted %s : %s", rootPath, name);
        logger.info(logMessage);

        Command command = new Command();

        command.setCommandType(COMMAND_TYPE.DELETED);
        command.setDate(new Date());
        command.setRootDirectory(rootPath);

        String filePath = getFilePath(rootPath, name);
        command.setFilePath(filePath);

        outgoingCommands.pushAndSave(command);
    }

    public void fileCreated(int wd, String rootPath, String name) {

        String logMessage = String.format("created %s : %s", rootPath, name);
        logger.info(logMessage);

        Command command = new Command();

        command.setCommandType(COMMAND_TYPE.CREATED);
        command.setDate(new Date());
        command.setRootDirectory(rootPath);

        String filePath = getFilePath(rootPath, name);
        command.setFilePath(filePath);

        outgoingCommands.pushAndSave(command);

    }

    private String getFilePath(String rootPath, String name) {
        String logMessage = String.format("get file path of %s -> %s", rootPath, name);
        logger.info(logMessage);
        return "";
    }



}
