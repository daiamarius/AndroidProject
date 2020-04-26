package com.example.statusapp.db.model.nodes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "techtag")
public class TechTagEntity {

    @PrimaryKey
    private int techTagId;
    private String name;

    public TechTagEntity(int techTagId, String name) {
        this.techTagId = techTagId;
        this.name = name;
    }

    public int getTechTagId() {
        return techTagId;
    }

    public void setTechTagId(int techTagId) {
        this.techTagId = techTagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TechTagEntity{" +
                "techTagId=" + techTagId +
                ", name='" + name + '\'' +
                '}';
    }
}
