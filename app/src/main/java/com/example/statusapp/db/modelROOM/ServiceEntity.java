package com.example.statusapp.db.modelROOM;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "service")
public class ServiceEntity {

    @PrimaryKey
    private int serviceId;
    private String name;
    private int passing;
    private int failing;
    private int warning;


    public ServiceEntity(int serviceId, String name, int passing, int failing, int warning) {
        this.serviceId = serviceId;
        this.name = name;
        this.passing = passing;
        this.failing = failing;
        this.warning = warning;
    }

    public int getPassing() {
        return passing;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }

    public int getFailing() {
        return failing;
    }

    public void setFailing(int failing) {
        this.failing = failing;
    }

    public int getWarning() {
        return warning;
    }

    public void setWarning(int warning) {
        this.warning = warning;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getName() {
        return name;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "serviceId=" + serviceId +
                ", name='" + name + '\'' +
                ", passing=" + passing +
                ", failing=" + failing +
                ", warning=" + warning +
                '}';
    }
}
