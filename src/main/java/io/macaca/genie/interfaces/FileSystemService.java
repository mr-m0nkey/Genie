package io.macaca.genie.interfaces;

import io.macaca.genie.models.FileModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileSystemService {

    FileModel getFileSystem(File directory);

    List<String> getRootDirectories();

    String getRootFromPath(String absolutePath) throws FileNotFoundException;
}
