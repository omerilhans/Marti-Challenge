package com.omerilhanli.martichallenge

import com.omerilhanli.martichallenge.di.component.DaggerAppComponent
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    private val applicationInjector = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun applicationInjector() = applicationInjector

    override fun onCreate() {
        super.onCreate()
        //
    }

}