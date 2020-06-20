package com.omerilhanli.api_ktx.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.omerilhanli.api_ktx.model.place.Place
import java.io.Serializable

data class PlacesResponse(
    @SerializedName("results")
    @Expose
    var results: List<Place>? = null,
    @SerializedName("status")
    @Expose
    var status: String? = null
): Serializable