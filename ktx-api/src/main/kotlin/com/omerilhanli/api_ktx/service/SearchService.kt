package com.omerilhanli.api_ktx.service

import com.omerilhanli.api_ktx.BuildConfig
import com.omerilhanli.api_ktx.common.Attribute.KEY_LOCATION
import com.omerilhanli.api_ktx.common.Attribute.KEY_PLACE_ID
import com.omerilhanli.api_ktx.common.Attribute.KEY_RADIUS
import com.omerilhanli.api_ktx.common.Attribute.KEY_TAG
import com.omerilhanli.api_ktx.common.Attribute.KEY_TYPE
import com.omerilhanli.api_ktx.common.EndPoint.KEY_NEARBY_PLACES
import com.omerilhanli.api_ktx.common.EndPoint.KEY_PLACE_DETAILS
import com.omerilhanli.api_ktx.model.PlaceDetailResponse
import com.omerilhanli.api_ktx.model.PlacesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET(KEY_NEARBY_PLACES)
    fun getPlaces(
        @Query(KEY_LOCATION) location: String,
        @Query(KEY_TYPE) type: String,
        @Query(KEY_RADIUS) radius: Int = 5000,
        @Query(KEY_TAG) key: String = BuildConfig.KEY_API
    ): Observable<PlacesResponse>

    @GET(KEY_PLACE_DETAILS)
    fun getPlaceDetails(
        @Query(KEY_PLACE_ID) placeId: String?,
        @Query(KEY_TAG) key: String = BuildConfig.KEY_API
    ): Observable<PlaceDetailResponse>
}