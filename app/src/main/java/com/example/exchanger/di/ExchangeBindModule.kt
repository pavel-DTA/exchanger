package com.example.exchanger.di

import com.example.exchanger.transformers.ExchangeDataTransformer
import com.example.exchanger.transformers.ExchangeNetworkDataTransformer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ExchangeBindModule {

    @Binds
    abstract fun bindExchangeDataTransformer(
        exchangeDataTransformer: ExchangeNetworkDataTransformer
    ): ExchangeDataTransformer
}