package com.example.statusapp.db.modelROOM;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usertag")
public class UserTagEntity {

    @PrimaryKey
    private int userTagId;

    private String name;

    public UserTagEntity(int userTagId, String name) {
        this.userTagId = userTagId;
        this.name = name;
    }

    public int getUserTagId() {
        return userTagId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserTagEntity{" +
                "userTagId=" + userTagId +
                ", name='" + name + '\'' +
                '}';
    }
}
