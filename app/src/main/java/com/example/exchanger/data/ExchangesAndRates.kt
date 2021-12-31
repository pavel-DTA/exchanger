package com.example.exchanger.data

import androidx.room.Embedded
import androidx.room.Relation

data class ExchangesAndRates(

    @Embedded
    var exchange: RemoteExchange,

    @Relation(
        parentColumn = "id",
        entityColumn = "exchange_id"
    )
    var rates: Rate?
)