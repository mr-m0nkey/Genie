package io.macaca.genie.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RootDirectory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    public String path;

    public String getPath() {
        return path;
    }
}
