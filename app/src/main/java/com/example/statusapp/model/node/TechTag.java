package com.example.statusapp.model.node;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "techtags")
public class TechTag {
    @SerializedName("name")
    @Expose
    private String name;

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int techTagId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTechTagId() {
        return techTagId;
    }

    public void setTechTagId(int techTagId) {
        this.techTagId = techTagId;
    }

    @Override
    public String toString() {
        return "TechTag{" +
                "name='" + name + '\'' +
                ", techTagId=" + techTagId +
                '}';
    }
}
