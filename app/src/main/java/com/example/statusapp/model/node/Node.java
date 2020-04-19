package com.example.statusapp.model.node;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.statusapp.model.Healthchecks;
import com.example.statusapp.model.service.Service;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "nodes",
        foreignKeys = @ForeignKey(
        entity = Service.class,
        parentColumns = "serviceId",
        childColumns = "serviceCreatorId",
        onDelete = CASCADE))
public class Node {

    @Ignore
    @SerializedName("healthchecks")
    @Expose
    private Healthchecks healthchecks;


    @SerializedName("host")
    @Expose
    private String host;

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int nodeId;

    @SerializedName("registered")
    @Expose
    private String registered;

    @SerializedName("tags")
    @Expose
    private ArrayList<TechTag> techTags;

    private int serviceCreatorId;

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

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public ArrayList<TechTag> getTechTags() {
        return techTags;
    }

    public void setTechTags(ArrayList<TechTag> techTags) {
        this.techTags = techTags;
    }

    @Override
    public String toString() {
        return "Node{" +
                "healthchecks=" + healthchecks +
                ", host='" + host + '\'' +
                ", nodeId=" + nodeId +
                ", registered='" + registered + '\'' +
                ", techTags=" + techTags +
                '}';
    }
}
