package com.example.rtireviews.service

import com.example.rtireviews.data.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideApiRepository(): ApiRepository {
        return ApiRepository()
    }
}