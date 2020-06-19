package com.omerilhanli.api_ktx.model.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OpeningHours implements Serializable {

    @SerializedName("open_now")
    @Expose
    private boolean open_now;

    @SerializedName("periods")
    @Expose
    private List<Period> periods;

    @SerializedName("weekday_text")
    @Expose
    private List<String> weekday_text;

    public boolean isOpen_now() {
        return open_now;
    }

    public void setOpen_now(boolean open_now) {
        this.open_now = open_now;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public List<String> getWeekday_text() {
        return weekday_text;
    }

    public void setWeekday_text(List<String> weekday_text) {
        this.weekday_text = weekday_text;
    }
}
