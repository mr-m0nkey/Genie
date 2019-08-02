package genie;

import genie.models.json.RootDirectories;
import genie.services.FilesystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Genie.class)
public class FilesystemTest {

    @Autowired
    private FilesystemService filesystemService;

    private String testPath = filesystemService.currentFileSystem + "/test";
    private File hiddenFile = new File(testPath + "/" + filesystemService.hiddenFileName);
    private File jsonFile = new File(filesystemService.currentFileSystem + "/" + filesystemService.jsonFileName);


    @Test
    public void creates_genie_file_in_new_root_directory() throws ExecutionException, InterruptedException {
        File file = new File(testPath);
        filesystemService.addRootPath(file.getPath(), new RootDirectories());

        Assert.assertTrue(hiddenFile.exists());
        Assert.assertTrue(hiddenFile.isHidden());


    }

    @Test
    public void generated_json_file_is_correct() throws InterruptedException, ExecutionException, IOException {


        filesystemService.addRootToJsonFile(new RootDirectories(), testPath);
        Assert.fail("Check if file content is correct");
        Assert.fail("Check if file content is same as root directory object");

    }

    @Test
    public void throws_an_exception_when_the_genie_file_is_missing_from_the_root_directory() {

    }

    @After
    public void cleanup() {

        hiddenFile.delete();
        jsonFile.delete();

    }


}
