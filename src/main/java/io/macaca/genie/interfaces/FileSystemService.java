package io.macaca.genie.interfaces;

import io.macaca.genie.entities.RootDirectory;
import io.macaca.genie.models.FileModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

public interface FileSystemService {

    FileModel getFileSystem(File directory);

    List<RootDirectory> getRootDirectories();

    String getRootFromPath(String absolutePath) throws FileNotFoundException;
}
