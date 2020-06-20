package com.omerilhanli.martichallenge.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.omerilhanli.api_ktx.model.PlaceDetailResponse
import com.omerilhanli.api_ktx.model.PlacesResponse
import com.omerilhanli.martichallenge.data.provider.SchedulerProvider
import com.omerilhanli.martichallenge.data.repository.SearchRepository
import com.omerilhanli.martichallenge.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    private val repository: SearchRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel<MainNavigator>() {

    var placeDetailResponse: PlaceDetailResponse?= null
    var placeTypeText: MutableLiveData<String> = MutableLiveData()

    val placesResponse: MutableLiveData<PlacesResponse> = MutableLiveData()
    val placeDetail: MutableLiveData<PlaceDetailResponse> = MutableLiveData()

    fun getPlaces(placeType: String) {
        compositeDisposable.add(
            repository.getPlaces(placeType)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribe({ response ->
                    placesResponse.value = response
                }, { ex ->
                    Log.e(ex.message, "operation failed.")
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

    fun getPlaceDetails(placeId: String) {
        compositeDisposable.add(
            repository.getPlaceDetail(placeId)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribe({ response ->
                    placeDetail.value = response
                }, { ex ->
                    Log.e(ex.message, "operation failed.")
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