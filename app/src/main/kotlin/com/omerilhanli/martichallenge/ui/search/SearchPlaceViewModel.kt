package com.omerilhanli.martichallenge.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.omerilhanli.api_ktx.model.PredictionResponse
import com.omerilhanli.martichallenge.utils.SchedulerProvider
import com.omerilhanli.martichallenge.data.SearchRepository
import com.omerilhanli.martichallenge.ui.base.BaseViewModel
import javax.inject.Inject

class SearchPlaceViewModel
@Inject constructor(
    private val repository: SearchRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel<SearchPlaceNavigator>() {

    val predications: MutableLiveData<PredictionResponse> = MutableLiveData()

    fun getPredications(input: String) {
        compositeDisposable.add(
            repository.loadPredication(input)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribe({ response ->
                    predications.value = response
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