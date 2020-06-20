package com.omerilhanli.martichallenge.ui.main

import androidx.lifecycle.ViewModelProvider
import com.omerilhanli.martichallenge.data.repository.SearchRepository
import com.omerilhanli.martichallenge.di.factory.ViewModelProviderFactory
import com.omerilhanli.martichallenge.data.provider.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    internal fun provideMainViewModel(
        repository: SearchRepository,
        scheduler: SchedulerProvider
    ): MainViewModel {
        return MainViewModel(repository = repository, scheduler = scheduler)
    }

    @Provides
    internal fun provideViewModelProvider(viewModel: MainViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}