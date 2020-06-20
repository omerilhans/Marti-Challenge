package com.omerilhanli.api_ktx.model.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Location(
    @SerializedName("lat")
    @Expose
    var lat: Double? = 0.0,
    @SerializedName("lng")
    @Expose
    var lng: Double? = 0.0
): Serializable