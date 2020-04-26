package com.example.statusapp.API.models.node;

import com.example.statusapp.API.models.Healthchecks;
import com.example.statusapp.API.models.Tag;
import com.example.statusapp.db.model.ServiceEntity;
import com.example.statusapp.db.model.nodes.NodeEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Node {

    @SerializedName("checks")
    @Expose
    private Healthchecks healthchecks;

    @SerializedName("name")
    @Expose
    private String name;

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

    public NodeEntity toEntity(){
        return new NodeEntity(this.id,this.name,this.healthchecks.getPassing(),this.healthchecks.getCritical(),
                this.healthchecks.getWarning(),this.host,this.registered);
    }
}
