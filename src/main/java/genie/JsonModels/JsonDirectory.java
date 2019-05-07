package genie.JsonModels;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonDirectory extends JsonFileRepresentation {

    private List<JsonFileRepresentation> content;

    public List<JsonFileRepresentation> getContent() {
        return content;
    }

    public void setContent(List<JsonFileRepresentation> content) {
        this.content = content;
    }
}
