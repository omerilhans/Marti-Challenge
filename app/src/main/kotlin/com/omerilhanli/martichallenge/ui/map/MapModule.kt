package com.omerilhanli.martichallenge.ui.map

import androidx.lifecycle.ViewModelProvider
import com.omerilhanli.martichallenge.di.factory.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class MapModule {

    @Provides
    internal fun provideMapViewModel(): MapViewModel {
        return MapViewModel()
    }

    @Provides
    internal fun provideViewModelProvider(viewModel: MapViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}