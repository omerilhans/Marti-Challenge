package com.omerilhanli.api_ktx.model.place;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.omerilhanli.api_ktx.model.common.Geometry;
import com.omerilhanli.api_ktx.model.common.OpeningHours;

import java.io.Serializable;
import java.util.List;

public class Place implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("geometry")
    @Expose
    private Geometry geometry;

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("opening_hours")
    @Expose
    private OpeningHours opening_hours;

    @SerializedName("place_id")
    @Expose
    private String place_id;

    @SerializedName("rating")
    @Expose
    private double rating;

    @SerializedName("scope")
    @Expose
    private String scope;

    @SerializedName("types")
    @Expose
    private List<String> types;

    @SerializedName("user_ratings_total")
    @Expose
    private int user_ratings_total;

    @SerializedName("vicinity")
    @Expose
    private String vicinity;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(OpeningHours opening_hours) {
        this.opening_hours = opening_hours;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public int getUser_ratings_total() {
        return user_ratings_total;
    }

    public void setUser_ratings_total(int user_ratings_total) {
        this.user_ratings_total = user_ratings_total;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
}
