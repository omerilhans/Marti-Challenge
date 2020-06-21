package com.omerilhanli.martichallenge.di.module

import androidx.lifecycle.ViewModel
import com.omerilhanli.martichallenge.di.factory.ViewModelKey
import com.omerilhanli.martichallenge.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ModuleViewModel {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModelMain: MainViewModel): ViewModel

}
