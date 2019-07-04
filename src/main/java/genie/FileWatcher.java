package genie;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileWatcher {

    private static final Logger logger = LoggerFactory.getLogger(FileWatcher.class);

    public void setupFileListener(String p) {

        int mask = JNotify.FILE_CREATED  |
                JNotify.FILE_DELETED  |
                JNotify.FILE_MODIFIED |
                JNotify.FILE_RENAMED;

        boolean watchSubtree = true;

        try {
            JNotify.addWatch(p, mask, watchSubtree, new FileListener());
        } catch (JNotifyException e) {
            logger.error("jnotify error", e);
        }



    }

}
