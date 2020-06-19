package com.omerilhanli.api_ktx.model.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Viewport implements Serializable {

    @SerializedName("northeast")
    @Expose
    private Location northeast;

    @SerializedName("southwest")
    @Expose
    private Location southwest;

    public Location getNortheast() {
        return northeast;
    }

    public void setNortheast(Location northeast) {
        this.northeast = northeast;
    }

    public Location getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Location southwest) {
        this.southwest = southwest;
    }
}
