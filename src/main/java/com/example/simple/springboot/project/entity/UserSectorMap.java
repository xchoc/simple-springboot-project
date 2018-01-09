package com.example.simple.springboot.project.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserSectorMap {

    @EmbeddedId
    private UserSectorMapPK id;

    public UserSectorMapPK getId() {
        return id;
    }

    public void setId(UserSectorMapPK id) {
        this.id = id;
    }
}
