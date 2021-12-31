package com.example.exchanger.data.sources

import com.example.exchanger.api.ExchangeApiService
import com.example.exchanger.data.RemoteExchange
import com.example.exchanger.util.ACCESS_KEY
import com.example.exchanger.util.ErrorContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.RuntimeException

@Singleton
class ExchangeNetworkDataSource @Inject constructor(
    private val api: ExchangeApiService,
    private val errCtx: ErrorContext
) {
    suspend fun getRates(): RemoteExchange? {
        return try {
            api.getExchangeRates(accessKey = ACCESS_KEY)
        } catch (e: RuntimeException) {
            errCtx.errorMessage = e.message
            null
        }
    }

}