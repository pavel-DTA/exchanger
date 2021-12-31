package com.example.exchanger.di

import com.example.exchanger.data.CurrencyGraph
import com.example.exchanger.util.Currency
import com.example.exchanger.util.ErrorContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object CommonModule {

    @Provides
    fun provideErrorContext(): ErrorContext {
        return ErrorContext()
    }

    @Provides
    fun provideCurrencyGraph() = CurrencyGraph(Currency.EUR.toString())
}