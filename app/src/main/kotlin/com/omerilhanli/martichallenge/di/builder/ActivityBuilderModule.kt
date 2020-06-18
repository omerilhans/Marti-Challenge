package com.omerilhanli.martichallenge.di.builder

import com.omerilhanli.martichallenge.ui.search.SearchPlaceActivity
import com.omerilhanli.martichallenge.di.module.ServiceModule
import com.omerilhanli.martichallenge.ui.detail.DetailActivity
import com.omerilhanli.martichallenge.ui.detail.DetailModule
import com.omerilhanli.martichallenge.ui.map.MapActivity
import com.omerilhanli.martichallenge.ui.map.MapModule
import com.omerilhanli.martichallenge.ui.search.SearchPlaceModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MapModule::class])
     abstract fun contributeMapActivity(): MapActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
     abstract fun contributeDetailActivity(): DetailActivity

    @ContributesAndroidInjector(modules = [SearchPlaceModule::class])
     abstract fun contributeSearchPlaceActivity(): SearchPlaceActivity
}