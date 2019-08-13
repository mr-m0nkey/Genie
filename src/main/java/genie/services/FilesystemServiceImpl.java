package genie.services;

import genie.exceptions.NotADirectoryException;
import genie.interfaces.FileSystemService;
import genie.models.FileModel;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FilesystemServiceImpl implements FileSystemService {

    @Override
    public FileModel getFileSystem(File directory) {
        if (!directory.isDirectory()) {
            throw new NotADirectoryException();
        }


        return buildFilesystem(directory);

    }

    private FileModel buildFilesystem(File file) {
        FileModel fileModel = new FileModel(file);
        return fileModel;
    }

    void getContent(FileModel fileModel, File file) {
        if (file.isDirectory()) {
            for (File fileIter : file.listFiles()) {
                fileModel.addFile(fileIter);
                getContent(new FileModel(fileIter), fileIter);
            }
        }

    }


}
