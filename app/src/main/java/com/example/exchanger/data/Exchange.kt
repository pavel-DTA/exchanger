package com.example.exchanger.data

import com.squareup.moshi.Json

open class Exchange {

    @Json(name = "rates")
    var rates: Rate? = null

}