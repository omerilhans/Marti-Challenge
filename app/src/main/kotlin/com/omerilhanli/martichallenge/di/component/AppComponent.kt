package com.omerilhanli.martichallenge.di.component

import com.omerilhanli.martichallenge.App
import com.omerilhanli.martichallenge.di.module.ApplicationModule
import com.omerilhanli.martichallenge.di.module.ServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppComponent
    }
}