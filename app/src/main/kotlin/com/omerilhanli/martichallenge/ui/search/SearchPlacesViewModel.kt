package com.omerilhanli.martichallenge.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.omerilhanli.api_ktx.model.PlacesResponse
import com.omerilhanli.martichallenge.utils.SchedulerProvider
import com.omerilhanli.martichallenge.data.SearchPlacesRepository
import com.omerilhanli.martichallenge.ui.base.BaseViewModel
import javax.inject.Inject

class SearchPlacesViewModel
@Inject constructor(
    private val placesRepository: SearchPlacesRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel<SearchPlacesNavigator>() {

    var placeTypeText: MutableLiveData<String> = MutableLiveData()

    val placesResponse: MutableLiveData<PlacesResponse> = MutableLiveData()

    fun getPlaces(placeType: String) {
        compositeDisposable.add(
            placesRepository.getPlaces(placeType)
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
}