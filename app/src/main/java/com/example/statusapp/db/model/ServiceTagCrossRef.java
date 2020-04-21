package com.example.statusapp.db.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "service_tag_join",
        primaryKeys = {"serviceId","userTagId"},
        foreignKeys = {
        @ForeignKey(entity = ServiceEntity.class,
                    parentColumns = "serviceId",
                    childColumns = "serviceId"),
        @ForeignKey(entity = UserTagEntity.class,
                    parentColumns = "userTagId",
                    childColumns = "userTagId")
        })
public class ServiceTagCrossRef {
    private int serviceId;
    private int userTagId;

    public ServiceTagCrossRef(int serviceId, int userTagId) {
        this.serviceId = serviceId;
        this.userTagId = userTagId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public int getUserTagId() {
        return userTagId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public void setUserTagId(int userTagId) {
        this.userTagId = userTagId;
    }
}
