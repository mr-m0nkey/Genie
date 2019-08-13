package genie;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import genie.interfaces.FileSystemService;
import genie.models.FileModel;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Genie.class)
public class FilesystemTest {

    private final String testFolder = "test";
    @Autowired
    private FileSystemService filesystemService;

    @Test
    public void can_create_file_system_json_file() {
        File baseDirectory = new File(testFolder);
        FileModel fileSystem = filesystemService.getFileSystem(baseDirectory);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Assertions
                    .assertThat(mapper.writeValueAsString(fileSystem))
                    .isEqualTo("{\"name\":\"test\",\"content\":[{\"name\":\"file-1\",\"content\":[{\"name\":\"file-1.txt\",\"content\":[],\"directory\":false},{\"name\":\"fff\",\"content\":[{\"name\":\"gdr.txt\",\"content\":[],\"directory\":false}],\"directory\":true},{\"name\":\"j\",\"content\":[],\"directory\":true}],\"directory\":true},{\"name\":\"aaa.txt\",\"content\":[],\"directory\":false}],\"directory\":true}");
        } catch (JsonProcessingException e) {
            Assertions.fail(e.getMessage());
        }
        
    }



}
