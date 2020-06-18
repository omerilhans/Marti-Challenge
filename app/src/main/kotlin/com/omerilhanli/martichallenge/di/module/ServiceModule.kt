package com.omerilhanli.martichallenge.di.module

import com.omerilhanli.api_ktx.service.SearchService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ServiceModule {

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }
}