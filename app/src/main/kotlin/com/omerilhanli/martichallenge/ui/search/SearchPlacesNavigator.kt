package com.omerilhanli.martichallenge.ui.search

import com.omerilhanli.api_ktx.model.place.Place
import com.omerilhanli.martichallenge.ui.base.BaseNavigator

interface SearchPlacesNavigator : BaseNavigator {

    fun navigateMapActivity(place: Place)
}