package com.omerilhanli.martichallenge.ui.search

import androidx.lifecycle.ViewModelProvider
import com.omerilhanli.martichallenge.data.SearchPlacesRepository
import com.omerilhanli.martichallenge.di.factory.ViewModelProviderFactory
import com.omerilhanli.martichallenge.utils.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SearchPlacesModule {

    @Provides
    internal fun provideSearchPlaceViewModel(
        searchPlacesRepository: SearchPlacesRepository,
        schedulerProvider: SchedulerProvider
    ): SearchPlacesViewModel {
        return SearchPlacesViewModel(placesRepository = searchPlacesRepository, scheduler = schedulerProvider)
    }

    @Provides
    internal fun provideViewModelProvider(viewModel: SearchPlacesViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}