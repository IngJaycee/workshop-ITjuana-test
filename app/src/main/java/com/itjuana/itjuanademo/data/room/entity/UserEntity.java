package com.itjuana.itjuanademo.data.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
@Entity(tableName = "USER_TABLE")
public class UserEntity {
    @PrimaryKey()
    private long id;

    private String name;

    private String description;

    public UserEntity(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @NonNull
    @Ignore
    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
