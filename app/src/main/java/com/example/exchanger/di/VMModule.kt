package com.example.exchanger.di

import com.example.exchanger.util.Currency
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object VMModule {

    @Provides
    fun provideCurrencyList() = arrayListOf(
        Currency.EUR,
        Currency.USD,
        Currency.GBP,
        Currency.RUB,
        Currency.CHF,
        Currency.CNY
    )
}