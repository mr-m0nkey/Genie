package io.macaca.genie.interfaces;

import io.macaca.genie.models.FileModel;

import java.io.File;

public interface FileSystemService {

    FileModel getFileSystem(File directory);
}
