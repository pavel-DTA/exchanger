package com.example.exchanger.di

import com.example.exchanger.api.ExchangeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ExchangeApiModule {

    @Provides
    fun provideExchangeApiService(): ExchangeApiService {
        return ExchangeApiService.create()
    }
}