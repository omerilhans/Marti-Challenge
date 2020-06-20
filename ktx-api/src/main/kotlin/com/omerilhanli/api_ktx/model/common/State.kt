package com.omerilhanli.api_ktx.model.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class State(
    @SerializedName("day")
    @Expose
    var day: Int? = 0,
    @SerializedName("time")
    @Expose
    var time: String? = null
): Serializable