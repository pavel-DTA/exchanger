package com.example.exchanger.transformers

import com.example.exchanger.data.CurrencyGraph
import javax.inject.Inject

class ExchangeNullDataTransformer @Inject constructor(
    graph: CurrencyGraph
) : ExchangeDataTransformer(graph) {
    override fun transformData(data: Any?): Nothing? = null

    override fun canTransform(data: Any?) = data === null

}