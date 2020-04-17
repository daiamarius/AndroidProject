package com.example.statusapp.model.node;

import com.example.statusapp.model.Healthchecks;
import com.example.statusapp.model.Tag;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Node {

    @SerializedName("healthchecks")
    @Expose
    private Healthchecks healthchecks;


    @SerializedName("host")
    @Expose
    private String host;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("registered")
    @Expose
    private String registered;

    @SerializedName("tags")
    @Expose
    private ArrayList<Tag> tags;

    public Healthchecks getHealthchecks() {
        return healthchecks;
    }

    public void setHealthchecks(Healthchecks healthchecks) {
        this.healthchecks = healthchecks;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Node{" +
                "healthchecks=" + healthchecks +
                ", host='" + host + '\'' +
                ", id=" + id +
                ", registered='" + registered + '\'' +
                ", tags=" + tags +
                '}';
    }
}
