package genie.models.json;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class JsonDirectory extends JsonFileRepresentation {

    private List<JsonFileRepresentation> content;

    public List<JsonFileRepresentation> getContent() {
        return content;
    }

    public void setContent(List<JsonFileRepresentation> content) {
        this.content = content;
    }

    @JsonIgnore
    public JsonFileRepresentation getFile(String name) {

        for(JsonFileRepresentation file : content) {
            if(file.getName().equals(name)) {
                return file;
            }
        }

        return null;
    }

    @JsonIgnore
    public void addFile(JsonFileRepresentation file) {
        content.add(file);
    }
}
