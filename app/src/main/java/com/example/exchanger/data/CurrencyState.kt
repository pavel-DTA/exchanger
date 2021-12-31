package com.example.exchanger.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "currency_states")
data class CurrencyState(

    @PrimaryKey var id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "from_position")
    var fromPosition: Int = 0,

    @ColumnInfo(name = "to_position")
    var toPosition: Int = 0,

    @ColumnInfo(name = "from_amount")
    var fromAmount: Int = 1,

    @ColumnInfo(name = "generated_date") var generatedDate: Calendar = Calendar.getInstance(),
)