package genie;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Queue;

@Service
public class FileWatcher {



    @Autowired
    Queue<Command> commandQueue;


    public void setupFileListener(String p) {

        int mask = JNotify.FILE_CREATED  |
                JNotify.FILE_DELETED  |
                JNotify.FILE_MODIFIED |
                JNotify.FILE_RENAMED;

        boolean watchSubtree = true;

        try {
            int watchID = JNotify.addWatch(p, mask, watchSubtree, new FileListener());
        } catch (JNotifyException e) {
            //TODO handle exception
            e.printStackTrace();
        }



    }

}
