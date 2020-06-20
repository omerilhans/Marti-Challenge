package com.omerilhanli.api_ktx.model.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Viewport(
    @SerializedName("northeast")
    @Expose
    var northeast: Location? = null,
    @SerializedName("southwest")
    @Expose
    var southwest: Location? = null
): Serializable