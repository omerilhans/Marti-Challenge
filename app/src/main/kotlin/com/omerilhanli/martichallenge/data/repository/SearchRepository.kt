package com.omerilhanli.martichallenge.data.repository

import com.omerilhanli.api_ktx.service.SearchService
import javax.inject.Inject

class SearchRepository
@Inject
constructor(private val searchService: SearchService) {

    fun getPlaces(placeType: String, locationStr: String) = searchService.getPlaces(location = locationStr, type = placeType)

    fun getPlaceDetail(placeId: String?) = searchService.getPlaceDetails(placeId)
}