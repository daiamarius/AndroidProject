package com.example.statusapp.modelAPI.service;

import com.example.statusapp.db.modelROOM.ServiceEntity;
import com.example.statusapp.modelAPI.Healthchecks;
import com.example.statusapp.modelAPI.Tag;
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

    public ServiceEntity toEntity(){
        return new ServiceEntity(this.id,this.name,this.checks.getPassing(),
                this.checks.getCritical(),this.checks.getWarning());
    }
}
