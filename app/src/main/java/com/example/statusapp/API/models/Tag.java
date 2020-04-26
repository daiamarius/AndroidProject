package com.example.statusapp.API.models;

import com.example.statusapp.db.model.UserTagEntity;
import com.example.statusapp.db.model.nodes.TechTagEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("id")
    @Expose
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public UserTagEntity toUserTag(){
        return new UserTagEntity(this.id,this.name);
    }

    public TechTagEntity toTechTag(){
        return new TechTagEntity(this.id,this.name);
    }
}
