package com.example.statusapp.model.service;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.statusapp.model.Healthchecks;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity(tableName = "services")
public class Service {
    @Ignore
    @SerializedName("healthchecks")
    @Expose
    private Healthchecks checks;

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int serviceId;

    @SerializedName("name")
    @Expose
    private String name;

    @Ignore
    @SerializedName("tags")
    @Expose
    private ArrayList<UserTag> userTags;

    public Service(int serviceId, String name, ArrayList<UserTag> userTags, Healthchecks checks) {
        this.checks = checks;
        this.serviceId = serviceId;
        this.name = name;
        this.userTags = userTags;
    }

    public Service(int serviceId, String name, ArrayList<UserTag> userTags) {
        this.checks = null;
        this.serviceId = serviceId;
        this.name = name;
        this.userTags = userTags;
    }

    public Service(int serviceId, String name) {
        this.checks = null;
        this.serviceId = serviceId;
        this.name = name;
        this.userTags = null;
    }

    public Healthchecks getChecks() {
        return checks;
    }

    public void setChecks(Healthchecks checks) {
        this.checks = checks;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<UserTag> getUserTags() {
        return userTags;
    }

    public void setUserTags(ArrayList<UserTag> userTags) {
        this.userTags = userTags;
    }

    @Override
    public String toString() {
        return "Service{" +
                "checks=" + checks +
                ", serviceId=" + serviceId +
                ", name='" + name + '\'' +
                ", userTags=" + userTags +
                '}';
    }
}
