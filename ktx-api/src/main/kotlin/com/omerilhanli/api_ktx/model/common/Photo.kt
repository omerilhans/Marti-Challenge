package com.omerilhanli.api_ktx.model.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Photo(
    @SerializedName("height")
    @Expose
    val height: Int? = null,
    @SerializedName("width")
    @Expose
    val width: Int? = null,
    @SerializedName("photo_reference")
    @Expose
    val photo_reference: String? = null
) : Serializable