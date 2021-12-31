package com.example.exchanger.data.sources

import com.example.exchanger.data.RemoteExchange
import com.example.exchanger.data.ExchangeDao
import com.example.exchanger.data.ExchangesAndRates
import com.example.exchanger.data.Rate
import com.example.exchanger.util.ErrorContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeLocalDataSource @Inject constructor(
    private val ctx: ErrorContext,
    private val exchangeDao: ExchangeDao
    ) {

    suspend fun getLastRates(): ExchangesAndRates? = exchangeDao.getLastRates()

    suspend fun addExchange(exchange: RemoteExchange) {
        exchangeDao.addExchange(exchange)
    }

    suspend fun updateExchange(exchange: RemoteExchange) {
        exchangeDao.updateExchange(exchange)
    }

    suspend fun addRate(rate: Rate) {
        exchangeDao.addRate(rate)
    }

    suspend fun updateRate(rate: Rate) {
        exchangeDao.updateRate(rate)
    }

}