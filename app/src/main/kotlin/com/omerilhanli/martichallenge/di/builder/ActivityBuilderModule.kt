package com.omerilhanli.martichallenge.di.builder

import com.omerilhanli.martichallenge.ui.main.MainActivity
import com.omerilhanli.martichallenge.ui.main.MainModule
import com.omerilhanli.martichallenge.ui.main.fragment.DetailFragment
import com.omerilhanli.martichallenge.ui.main.fragment.MapFragment
import com.omerilhanli.martichallenge.ui.main.fragment.SearchPlacesFragment
import com.omerilhanli.martichallenge.ui.splash.SplashActivity
import com.omerilhanli.martichallenge.ui.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    //region Just activity
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun contributeSplashActivity(): SplashActivity
    //endregion


    //region Activity with fragments
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeSearchPlacesFragment(): SearchPlacesFragment

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMapFragment(): MapFragment

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeDetailFragment(): DetailFragment
    //endregion
}