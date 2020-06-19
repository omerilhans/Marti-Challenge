package com.omerilhanli.api_ktx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.omerilhanli.api_ktx.model.place.Place;

import java.io.Serializable;
import java.util.List;

public class PlacesResponse implements Serializable {

    @SerializedName("results")
    @Expose
    private List<Place> results;

    @SerializedName("status")
    @Expose
    private String status;

    public List<Place> getResults() {
        return results;
    }

    public void setResults(List<Place> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
