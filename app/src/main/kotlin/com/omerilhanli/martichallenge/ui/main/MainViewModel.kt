package com.omerilhanli.martichallenge.ui.main

import androidx.lifecycle.MutableLiveData
import com.omerilhanli.api_ktx.model.PlaceDetailResponse
import com.omerilhanli.api_ktx.model.PlacesResponse
import com.omerilhanli.martichallenge.data.provider.SchedulerProvider
import com.omerilhanli.martichallenge.data.repository.SearchRepository
import com.omerilhanli.martichallenge.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    val repository: SearchRepository,
    val scheduler: SchedulerProvider
) : BaseViewModel<MainNavigator>() {

    var placeDetailResponse: PlaceDetailResponse? = null
    var placeTypeText: MutableLiveData<String> = MutableLiveData()

    val placesResponse: MutableLiveData<PlacesResponse> = MutableLiveData()
    val placeDetail: MutableLiveData<PlaceDetailResponse> = MutableLiveData()

    fun getPlaces(placeType: String, locationStr: String) {
        compositeDisposable.add(
            repository.getPlaces(placeType, locationStr)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribe({ response ->
                    placesResponse.value = response
                }, { ex ->
                    setIsLoading(false)
                    navigator?.handleError(ex)
                }, {
                    setIsLoading(false)
                }, {
                    setIsLoading(true)
                }
                )
        )
    }

    fun getPlaceDetails(placeId: String?) {
        compositeDisposable.add(
            repository.getPlaceDetail(placeId)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribe({ response ->
                    placeDetail.value = response
                }, { ex ->
                    setIsLoading(false)
                    navigator?.handleError(ex)
                }, {
                    setIsLoading(false)
                }, {
                    setIsLoading(true)
                }
                )
        )
    }

}