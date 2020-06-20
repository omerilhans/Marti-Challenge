package com.omerilhanli.api_ktx.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.omerilhanli.api_ktx.model.placedetail.PlaceDetail
import java.io.Serializable

data class PlaceDetailResponse(
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("result")
    @Expose
    var result: PlaceDetail? = null
) : Serializable