package com.example.exchanger.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.*

@Entity(
    tableName = "exchanges",
    ignoredColumns = ["rates"]
)
data class RemoteExchange(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),

    @Json(name = "base")
    @ColumnInfo(name = "base")
    var base: String,

    @Transient
    @ColumnInfo(name = "generated_date")
    var generatedDate: Calendar = Calendar.getInstance(),

) : Exchange()