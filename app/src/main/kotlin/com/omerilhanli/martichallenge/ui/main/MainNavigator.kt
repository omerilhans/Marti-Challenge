package com.omerilhanli.martichallenge.ui.main

import com.omerilhanli.api_ktx.model.PlaceDetailResponse
import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.martichallenge.ui.base.BaseNavigator

interface MainNavigator : BaseNavigator {
    fun showSearchPlacesFragment()
    fun showMapFragment(place:Place)
    fun showPlaceDetailFragment(placeDetailResponse: PlaceDetailResponse)
}