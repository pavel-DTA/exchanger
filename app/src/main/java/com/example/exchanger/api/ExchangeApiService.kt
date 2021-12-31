package com.example.exchanger.api

import com.example.exchanger.data.RemoteExchange
import com.example.exchanger.util.EXCHANGE_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface ExchangeApiService {

    @GET("v1/latest")
    suspend fun getExchangeRates(
        @Query("access_key") accessKey: String,
    ): RemoteExchange

    companion object {
        fun create(): ExchangeApiService {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .build()

            val logger = HttpLoggingInterceptor().apply { level =
                HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .baseUrl(EXCHANGE_BASE_URL)
                .build()
                .create(ExchangeApiService::class.java)
        }
    }
}