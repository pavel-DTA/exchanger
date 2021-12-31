package com.example.exchanger.di

import com.example.exchanger.data.CurrencyGraph
import com.example.exchanger.transformers.ExchangeLocalDataTransformer
import com.example.exchanger.transformers.ExchangeNetworkDataTransformer
import com.example.exchanger.transformers.ExchangeNullDataTransformer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ExchangeTransformerModule {

    @Provides
    fun provideExchangeNetworkDataTransformer(
        next: ExchangeLocalDataTransformer,
        graph: CurrencyGraph
    ): ExchangeNetworkDataTransformer {
        return ExchangeNetworkDataTransformer(graph).also { it.next = next }
    }

    @Provides
    fun provideExchangeLocalDataTransformer(
        next: ExchangeNullDataTransformer,
        graph: CurrencyGraph
    ): ExchangeLocalDataTransformer {
        return ExchangeLocalDataTransformer(graph).also { it.next = next }
    }

    @Provides
    fun provideExchangeNullDataTransformer(graph: CurrencyGraph): ExchangeNullDataTransformer {
        return ExchangeNullDataTransformer(graph)
    }

}