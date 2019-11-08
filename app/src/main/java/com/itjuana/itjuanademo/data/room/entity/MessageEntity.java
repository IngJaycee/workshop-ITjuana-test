package com.itjuana.itjuanademo.data.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
@Entity(tableName = "MESSAGE_TABLE")
public class MessageEntity {
    @NonNull
    @PrimaryKey()
    private String id;

    private String body;

    private String name;

    public MessageEntity(@NotNull String id, String body, String name) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
