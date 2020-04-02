package io.macaca.genie.entities;

import javax.persistence.Entity;

@Entity
public class RootDirectory {

    public Long id;

    public String path;

    public String getPath() {
        return path;
    }
}
