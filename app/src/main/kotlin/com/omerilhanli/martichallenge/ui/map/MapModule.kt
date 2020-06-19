package com.omerilhanli.martichallenge.ui.map

import androidx.lifecycle.ViewModelProvider
import com.omerilhanli.martichallenge.data.SearchPlacesRepository
import com.omerilhanli.martichallenge.di.factory.ViewModelProviderFactory
import com.omerilhanli.martichallenge.utils.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class MapModule {

    @Provides
    internal fun provideMapViewModel(
        searchPlacesRepository: SearchPlacesRepository,
        schedulerProvider: SchedulerProvider
    ): MapViewModel {
        return MapViewModel(searchPlacesRepository, schedulerProvider)
    }

    @Provides
    internal fun provideViewModelProvider(viewModel: MapViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}