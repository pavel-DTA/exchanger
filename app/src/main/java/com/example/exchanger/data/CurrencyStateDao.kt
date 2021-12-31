package com.example.exchanger.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyStateDao {

    @Query("SELECT * FROM currency_states ORDER BY generated_date DESC LIMIT 1")
    fun getLastState(): Flow<CurrencyState?>

    @Insert
    suspend fun addState(state: CurrencyState)

    @Update
    suspend fun updateState(state: CurrencyState)
}