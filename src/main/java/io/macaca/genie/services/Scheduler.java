package io.macaca.genie.services;


import io.macaca.genie.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;

@Service
@Slf4j
public class Scheduler {

    @Autowired
    private FileWatcherServiceImpl fileWatcherService;

    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        DataUtil.watchFiles.forEach(file -> {
            fileWatcherService.watchFiles(file);
            DataUtil.watchFiles.remove(file);
        });
    }
}
