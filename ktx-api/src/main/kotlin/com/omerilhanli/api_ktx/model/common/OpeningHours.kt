package com.omerilhanli.api_ktx.model.common

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OpeningHours(
    @SerializedName("open_now")
    @Expose
    var isOpen_now: Boolean = false,
    @SerializedName("periods")
    @Expose
    var periods: List<Period>? = null,
    @SerializedName("weekday_text")
    @Expose
    var weekday_text: List<String>? = null
): Serializable