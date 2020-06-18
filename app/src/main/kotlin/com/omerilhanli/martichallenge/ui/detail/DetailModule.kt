package com.omerilhanli.martichallenge.ui.detail

import androidx.lifecycle.ViewModelProvider
import com.omerilhanli.martichallenge.di.factory.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class DetailModule  {

    @Provides
    internal fun provideDetailViewModel(): DetailViewModel {
        return DetailViewModel()
    }

    @Provides
    internal fun provideViewModelProvider(viewModel: DetailViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}