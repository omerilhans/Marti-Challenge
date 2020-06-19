package com.omerilhanli.martichallenge.di.module

import com.omerilhanli.api_ktx.service.SearchService
import com.omerilhanli.martichallenge.data.SearchPlacesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideUserRepository(searchService: SearchService): SearchPlacesRepository {
        return SearchPlacesRepository(searchService)
    }
}