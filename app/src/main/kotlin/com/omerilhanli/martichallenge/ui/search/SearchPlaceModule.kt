package com.omerilhanli.martichallenge.ui.search

import androidx.lifecycle.ViewModelProvider
import com.omerilhanli.martichallenge.data.SearchRepository
import com.omerilhanli.martichallenge.di.factory.ViewModelProviderFactory
import com.omerilhanli.martichallenge.utils.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SearchPlaceModule {

    @Provides
    internal fun provideSearchPlaceViewModel(
        searchRepository: SearchRepository,
        schedulerProvider: SchedulerProvider
    ): SearchPlaceViewModel {
        return SearchPlaceViewModel(repository = searchRepository, scheduler = schedulerProvider)
    }

    @Provides
    internal fun provideViewModelProvider(viewModel: SearchPlaceViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}