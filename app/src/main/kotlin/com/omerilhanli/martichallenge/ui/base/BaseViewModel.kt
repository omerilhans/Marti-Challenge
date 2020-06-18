package com.omerilhanli.martichallenge.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N> : ViewModel() {

    open val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    open val compositeDisposable = CompositeDisposable()

    private var mNavigator: WeakReference<N>? = null

    var navigator: N?
        get() = mNavigator?.get()
        set(navigator) {
            this.mNavigator = if (navigator != null) WeakReference(navigator) else null
        }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}