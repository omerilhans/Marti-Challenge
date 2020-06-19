package com.omerilhanli.api_ktx.service

import com.omerilhanli.api_ktx.model.PlaceDetailResponse
import com.omerilhanli.api_ktx.model.PlacesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    companion object {
        const val KEY_API = "AIzaSyDI1uvBMNBrS008eT9W6s1AMNfG1wUS4t8"
    }

    @GET("api/place/nearbysearch/json")
    fun getPlaces(
        @Query("location") location: String,
        @Query("type") type: String,
        @Query("radius") radius: Int = 5000,
        @Query("key") key: String = KEY_API
    ): Observable<PlacesResponse>

    @GET("api/place/details/json")
    fun getPlaceDetails(
        @Query("placeid") placeId: String?,
        @Query("key") key: String = KEY_API
    ): Observable<PlaceDetailResponse>
}
/*
https://maps.googleapis.com/maps/api/place/nearbysearch/json?
location=41.0548963,28.834197&radius=1500&type=restaurant&keyword=yemek
&key=AIzaSyDI1uvBMNBrS008eT9W6s1AMNfG1wUS4t8
 */