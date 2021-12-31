package com.example.exchanger.data

import androidx.room.*

@Dao
interface ExchangeDao {

    @Transaction
    @Query("SELECT * FROM exchanges ORDER BY generated_date DESC LIMIT 1")
    suspend fun getLastRates(): ExchangesAndRates?

    @Insert
    suspend fun addExchange(exchange: RemoteExchange)

    @Update
    suspend fun updateExchange(exchange: RemoteExchange)

    @Insert
    suspend fun addRate(rate: Rate)

    @Update
    suspend fun updateRate(rate: Rate)

}