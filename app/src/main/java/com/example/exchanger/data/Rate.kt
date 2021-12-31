package com.example.exchanger.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.UUID

@Entity(tableName = "rates")
data class Rate(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = UUID.randomUUID().toString(),

    @Transient
    @ColumnInfo(name = "exchange_id")
    var exchangeId: String = "",

    @Json(name = "RUB")
    var rub: Double,

    @Json(name = "EUR")
    var eur: Double,

    @Json(name = "GBP")
    var gbp: Double,

    @Json(name = "USD")
    var usd: Double,

    @Json(name = "CHF")
    var chf: Double,

    @Json(name = "CNY")
    var cny: Double
)