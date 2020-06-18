package com.omerilhanli.api_ktx.service

import com.omerilhanli.api_ktx.model.PredictionResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("api/place/autocomplete/json?types=address&key=AIzaSyDI1uvBMNBrS008eT9W6s1AMNfG1wUS4t8")
    fun loadPredictions(@Query("input") address: String?): Observable<PredictionResponse?>
}