package com.example.statusapp.model.service;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "usertags")
public class UserTag {
    @SerializedName("name")
    @Expose
    private String name;

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int userTagId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserTagId() {
        return userTagId;
    }

    public void setUserTagId(int userTagId) {
        this.userTagId = userTagId;
    }

    @Override
    public String toString() {
        return "TechTag{" +
                "name='" + name + '\'' +
                ", userTagId=" + userTagId +
                '}';
    }
}

