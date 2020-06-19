package com.omerilhanli.api_ktx.model.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class State implements Serializable {

    @SerializedName("day")
    @Expose
    private int day;

    @SerializedName("time")
    @Expose
    private String time;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
