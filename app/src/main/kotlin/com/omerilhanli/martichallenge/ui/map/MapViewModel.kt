package com.omerilhanli.martichallenge.ui.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.omerilhanli.api_ktx.model.PlaceDetailResponse
import com.omerilhanli.martichallenge.data.SearchPlacesRepository
import com.omerilhanli.martichallenge.ui.base.BaseViewModel
import com.omerilhanli.martichallenge.utils.SchedulerProvider
import javax.inject.Inject

class MapViewModel
@Inject constructor(
    private val placesRepository: SearchPlacesRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel<MapNavigator>() {

    val placeDetail: MutableLiveData<PlaceDetailResponse> = MutableLiveData()

    fun getPlaceDetails(placeId: String) {
        compositeDisposable.add(
            placesRepository.getPlaceDetail(placeId)
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