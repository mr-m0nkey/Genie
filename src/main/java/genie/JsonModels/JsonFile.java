package genie.JsonModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonFile extends JsonFileRepresentation implements IJsonFile{
}
