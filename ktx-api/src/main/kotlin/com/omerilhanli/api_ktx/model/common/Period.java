package com.omerilhanli.api_ktx.model.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Period implements Serializable {

    @SerializedName("close")
    @Expose
    private State close;

    @SerializedName("open")
    @Expose
    private State open;

    public State getClose() {
        return close;
    }

    public void setClose(State close) {
        this.close = close;
    }

    public State getOpen() {
        return open;
    }

    public void setOpen(State open) {
        this.open = open;
    }
}
