package com.omerilhanli.api_ktx.model.place

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.omerilhanli.api_ktx.model.common.Geometry
import com.omerilhanli.api_ktx.model.common.OpeningHours
import com.omerilhanli.api_ktx.model.common.Photo
import java.io.Serializable

data class Place(
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("geometry")
    @Expose
    var geometry: Geometry? = null,
    @SerializedName("icon")
    @Expose
    var icon: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("opening_hours")
    @Expose
    var opening_hours: OpeningHours? = null,
    @SerializedName("photos")
    @Expose
    var photos: List<Photo>? = null,
    @SerializedName("place_id")
    @Expose
    var place_id: String? = null,
    @SerializedName("rating")
    @Expose
    var rating: Double? = 0.0,
    @SerializedName("scope")
    @Expose
    var scope: String? = null,
    @SerializedName("types")
    @Expose
    var types: List<String>? = null,
    @SerializedName("user_ratings_total")
    @Expose
    var user_ratings_total: Int = 0,
    @SerializedName("vicinity")
    @Expose
    var vicinity: String? = null
) : Serializable