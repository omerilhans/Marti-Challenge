package com.omerilhanli.martichallenge.ui.splash

import androidx.lifecycle.ViewModelProvider
import com.omerilhanli.martichallenge.di.factory.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    internal fun provideSplashViewModel(): SplashViewModel {
        return SplashViewModel()
    }

    @Provides
    internal fun provideViewModelProvider(viewModel: SplashViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}