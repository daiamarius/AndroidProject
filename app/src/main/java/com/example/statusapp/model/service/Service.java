package com.example.statusapp.model.service;

import com.example.statusapp.model.Healthchecks;
import com.example.statusapp.model.Tag;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Service {

    @SerializedName("healthchecks")
    @Expose
    private Healthchecks checks;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("tags")
    @Expose
    private ArrayList<Tag> tags;

    public Healthchecks getChecks() {
        return checks;
    }

    public void setChecks(Healthchecks checks) {
        this.checks = checks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Service{" +
                "checks=" + checks +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", tags=" + tags +
                '}';
    }
}
