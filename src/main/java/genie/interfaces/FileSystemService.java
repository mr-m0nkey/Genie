package genie.interfaces;

import genie.models.FileModel;

import java.io.File;

public interface FileSystemService {

    FileModel getFileSystem(File directory);
}
