package com.omerilhanli.api_ktx.model.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Geometry(
    @SerializedName("location")
    @Expose
    var location: Location? = null,
    @SerializedName("viewport")
    @Expose
    var viewport: Viewport? = null
): Serializable