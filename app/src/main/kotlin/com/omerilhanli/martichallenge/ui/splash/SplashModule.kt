package com.omerilhanli.martichallenge.ui.splash

import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    internal fun provideSplashViewModel(): SplashViewModel {
        return SplashViewModel()
    }
}