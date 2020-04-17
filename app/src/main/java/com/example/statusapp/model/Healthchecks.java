package com.example.statusapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Healthchecks {
    @SerializedName("passing")
    @Expose
    private int passing;

    @SerializedName("critical")
    @Expose
    private int critical;

    @SerializedName("warning")
    @Expose
    private int warning;

    public int getPassing() {
        return passing;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getWarning() {
        return warning;
    }

    public void setWarning(int warning) {
        this.warning = warning;
    }

    @Override
    public String toString() {
        return "Healthchecks{" +
                "passing=" + passing +
                ", critical=" + critical +
                ", warning=" + warning +
                '}';
    }
}

