package com.example.exchanger.di

import android.content.Context
import com.example.exchanger.data.AppDatabase
import com.example.exchanger.data.CurrencyStateDao
import com.example.exchanger.data.ExchangeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideCurrencyStateDao(db: AppDatabase): CurrencyStateDao = db.currencyStateDao()

    @Provides
    fun provideExchangeDao(db: AppDatabase): ExchangeDao = db.exchangeDao()
}