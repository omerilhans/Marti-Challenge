package com.omerilhanli.martichallenge.ui.detail

import com.omerilhanli.api_ktx.model.PlaceDetailResponse
import com.omerilhanli.martichallenge.ui.base.BaseViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor() : BaseViewModel<DetailNavigator>() {

    var placeDetailResponse: PlaceDetailResponse?= null

}