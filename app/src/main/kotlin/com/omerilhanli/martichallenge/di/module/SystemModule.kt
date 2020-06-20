package com.omerilhanli.martichallenge.di.module

import com.omerilhanli.martichallenge.data.provider.SchedulerProvider
import com.omerilhanli.martichallenge.data.provider.AppSchedulerProvider
import dagger.Module
import dagger.Provides

@Module
object SystemModule {

    @JvmStatic
    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}