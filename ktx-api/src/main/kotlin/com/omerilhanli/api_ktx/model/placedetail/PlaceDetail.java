package com.omerilhanli.api_ktx.model.placedetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.omerilhanli.api_ktx.model.common.Geometry;

import java.io.Serializable;
import java.util.List;

public class PlaceDetail implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("formatted_address")
    @Expose
    private String formatted_address;

    @SerializedName("formatted_phone_number")
    @Expose
    private String formatted_phone_number;

    @SerializedName("geometry")
    @Expose
    private Geometry geometry;

    @SerializedName("international_phone_number")
    @Expose
    private String international_phone_number;

    @SerializedName("name")
    @Expose
    private String name;

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

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getFormatted_phone_number() {
        return formatted_phone_number;
    }

    public void setFormatted_phone_number(String formatted_phone_number) {
        this.formatted_phone_number = formatted_phone_number;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInternational_phone_number() {
        return international_phone_number;
    }

    public void setInternational_phone_number(String international_phone_number) {
        this.international_phone_number = international_phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
