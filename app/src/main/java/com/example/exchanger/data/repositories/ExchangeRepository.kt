package com.example.exchanger.data.repositories

import android.content.Context
import com.example.exchanger.data.CurrencyGraph
import com.example.exchanger.data.RemoteExchange
import com.example.exchanger.data.sources.ExchangeLocalDataSource
import com.example.exchanger.data.sources.ExchangeNetworkDataSource
import com.example.exchanger.transformers.ExchangeDataTransformer
import com.example.exchanger.util.NetworkConnectionCheck
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeRepository @Inject constructor(
    @ApplicationContext private val ctx: Context,
    private val localDataSource: ExchangeLocalDataSource,
    private val networkDataSource: ExchangeNetworkDataSource,
    private val dataTransformer: ExchangeDataTransformer
) {
    suspend fun getRates(): CurrencyGraph? {
        return if (NetworkConnectionCheck.isConnect(ctx)) {
            val data = networkDataSource.getRates()
            if (data !== null) addRates(data)
            transformData(data)
        } else {
            val data = localDataSource.getLastRates()

            //Todo implements messenger
            if (data === null) { } else {}

            transformData(data)
        }
    }

    private suspend fun updateRates(exchange: RemoteExchange) {
        try {
            localDataSource.updateExchange(exchange)
            exchange.rates?.let { localDataSource.updateRate(it.apply { exchangeId = exchange.id }) }
        } catch(e: Exception) {
        }
    }

    private suspend fun addRates(exchange: RemoteExchange) {
        try {
            localDataSource.addExchange(exchange)
            exchange.rates?.let { localDataSource.addRate(it.apply { exchangeId = exchange.id }) }
        } catch(e: Exception) {
        }
    }

    private fun transformData(data: Any?) = dataTransformer.transform(data)
}