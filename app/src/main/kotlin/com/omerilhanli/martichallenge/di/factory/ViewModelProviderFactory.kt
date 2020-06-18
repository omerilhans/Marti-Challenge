package com.omerilhanli.martichallenge.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelProviderFactory<V>(private val viewModel: V) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            return viewModel as T
        } catch (t: Throwable) {
            throw t
        }
    }
}
