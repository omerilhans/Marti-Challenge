package com.omerilhanli.api_ktx.model.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Period(
    @SerializedName("close")
    @Expose
    var close: State? = null,
    @SerializedName("open")
    @Expose
    var open: State? = null
): Serializable