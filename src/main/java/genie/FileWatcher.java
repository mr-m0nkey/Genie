package genie;

import genie.models.json.RootDirectories;
import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Queue;

@Service
public class FileWatcher {



    @Autowired
    Queue<Command> commandQueue;


    //@Async
    public void getChangesInRootAndPopulateCommandQueue(String p) {

        int mask = JNotify.FILE_CREATED  |
                JNotify.FILE_DELETED  |
                JNotify.FILE_MODIFIED |
                JNotify.FILE_RENAMED;

        // watch subtree?
        boolean watchSubtree = true;

        // add actual watch
        try {
            int watchID = JNotify.addWatch(p, mask, watchSubtree, new FileListener());
        } catch (JNotifyException e) {

        }



    }

}
