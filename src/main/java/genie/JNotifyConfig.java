package genie;

import org.springframework.stereotype.Service;

@Service
public class JNotifyConfig {

    private int watchId;

    public int getWatchId() {
        return watchId;
    }

    public void setWatchId(int watchId) {
        this.watchId = watchId;
    }
}
