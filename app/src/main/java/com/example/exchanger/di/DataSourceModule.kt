package com.example.exchanger.di

import com.example.exchanger.api.ExchangeApiService
import com.example.exchanger.data.ExchangeDao
import com.example.exchanger.data.sources.ExchangeLocalDataSource
import com.example.exchanger.data.sources.ExchangeNetworkDataSource
import com.example.exchanger.util.ErrorContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    fun provideExchangeNetworkDataSource(
        api: ExchangeApiService,
        errorCtx: ErrorContext
    ) : ExchangeNetworkDataSource {
        return ExchangeNetworkDataSource(api, errorCtx)
    }

    @Provides
    fun provideExchangeLocalDataSource(errorCtx: ErrorContext, exchangeDao: ExchangeDao): ExchangeLocalDataSource {
        return ExchangeLocalDataSource(errorCtx, exchangeDao)
    }

}