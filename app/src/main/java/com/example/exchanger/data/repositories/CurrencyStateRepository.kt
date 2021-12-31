package com.example.exchanger.data.repositories

import com.example.exchanger.data.CurrencyState
import com.example.exchanger.data.CurrencyStateDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyStateRepository @Inject constructor(
    private val currencyStateDao: CurrencyStateDao
    ) {

    fun getLastCurrencyState(): Flow<CurrencyState?> = currencyStateDao.getLastState()

    suspend fun addState(state: CurrencyState) {
        currencyStateDao.addState(state)
    }

    suspend fun updateState(state: CurrencyState) {
        currencyStateDao.updateState(state)
    }
}