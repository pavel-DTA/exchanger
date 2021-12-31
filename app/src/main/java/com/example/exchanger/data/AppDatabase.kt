package com.example.exchanger.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.exchanger.util.DB_NAME

@Database(entities = [CurrencyState::class, RemoteExchange::class, Rate::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyStateDao(): CurrencyStateDao

    abstract fun exchangeDao(): ExchangeDao

    companion object {
        fun getInstance(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, DB_NAME
        ).build()
    }
}