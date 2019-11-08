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
@Entity(tableName = "MESSAGE_TABLE")
public class MessageEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String body;

    private String name;

    public MessageEntity(String body, String name) {
        this.body = body;
        this.name = name;
    }

    @Ignore
    @NonNull
    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
