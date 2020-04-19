package com.example.statusapp.model.service;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class ServiceWithTags {
    @Embedded public Service service;
    @Relation(
            parentColumn = "serviceId",
            entityColumn = "userTagId",
            associateBy = @Junction(ServiceTagsCrossRef.class)
    )
    public List<UserTag> tags;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<UserTag> getTags() {
        return tags;
    }

    public void setTags(List<UserTag> tags) {
        this.tags = tags;
    }
}
