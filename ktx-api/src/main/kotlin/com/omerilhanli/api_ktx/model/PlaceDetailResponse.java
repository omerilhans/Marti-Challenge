package com.omerilhanli.api_ktx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.omerilhanli.api_ktx.model.placedetail.PlaceDetail;

import java.io.Serializable;

public class PlaceDetailResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("result")
    @Expose
    private PlaceDetail result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PlaceDetail getResult() {
        return result;
    }

    public void setResult(PlaceDetail result) {
        this.result = result;
    }
}
