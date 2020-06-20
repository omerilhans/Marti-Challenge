package com.omerilhanli.martichallenge.data.repository

import com.omerilhanli.api_ktx.service.SearchService
import javax.inject.Inject

class SearchRepository
@Inject
constructor(private val searchService: SearchService) {

    fun getPlaces(placeType: String) =
        searchService.getPlaces(location = "41.0548963,28.834197", type = placeType)

    fun getPlaceDetail(placeId: String) = searchService.getPlaceDetails(placeId)
}