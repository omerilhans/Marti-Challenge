package com.omerilhanli.api_ktx.model.placedetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.omerilhanli.api_ktx.model.common.Geometry
import java.io.Serializable

data class PlaceDetail(
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("formatted_address")
    @Expose
    var formatted_address: String? = null,
    @SerializedName("formatted_phone_number")
    @Expose
    var formatted_phone_number: String? = null,
    @SerializedName("geometry")
    @Expose
    var geometry: Geometry? = null,
    @SerializedName("international_phone_number")
    @Expose
    var international_phone_number: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
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
): Serializable