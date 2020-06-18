package com.omerilhanli.martichallenge.di.module

import com.omerilhanli.martichallenge.utils.SchedulerProvider
import com.omerilhanli.martichallenge.utils.AppSchedulerProvider
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