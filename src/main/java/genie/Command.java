/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

import genie.enums.COMMAND_TYPE;
import genie.models.pojos.DeviceDetails;

import java.util.Date;

/**
 *
 * @author mayowa
 */
public class Command {

    private Date date;
    private COMMAND_TYPE commandType;
    private String rootDirectory;
    private String formerFilePath;
    private String filePath;
    private String deviceId;

    public Command() {
        deviceId = DeviceDetails.getDeviceId();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public COMMAND_TYPE getCommandType() {
        return commandType;
    }

    public void setCommandType(COMMAND_TYPE commandType) {
        this.commandType = commandType;
    }

    public String getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public String getFormerFilePath() {
        return formerFilePath;
    }

    public void setFormerFilePath(String formerFilePath) {
        this.formerFilePath = formerFilePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean perform() {
        
        return true;
    }


    
}
