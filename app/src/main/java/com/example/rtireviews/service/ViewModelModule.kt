package com.example.rtireviews.service

import com.example.rtireviews.data.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Singleton
    @Provides
    fun provideApiRepository(httpClient: HttpClient): ApiRepository {
        return ApiRepository(httpClient)
    }
}